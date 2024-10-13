package com.hjb.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hjb.admin.domain.question.*;
import com.hjb.admin.mapper.QuestionMapper;
import com.hjb.core.enums.ResCode;
import com.hjb.security.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    public List<QuestionVo> list(QuestionQueryDto questionQueryDto) {
        // 分页
        PageHelper.startPage(questionQueryDto.getPageNum(), questionQueryDto.getPageSize());
        return questionMapper.selectQuestionList(questionQueryDto);
    }

    // 添加
    public int add(QuestionAddDto questionAddDto) {
        // 判断重复性
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionAddDto.getTitle()));
        if (CollectionUtil.isNotEmpty(questionList)) {
            throw new ServiceException(ResCode.FAILED_QUESTION_EXISTS);
        }

        Question question = new Question();
        BeanUtil.copyProperties(questionAddDto, question);
        return questionMapper.insert(question);
    }

    // 查看
    public QuestionSearchVo search(Long questionId){
        Question question = questionMapper.selectById(questionId);
        if(question == null){
            throw new ServiceException(ResCode.FAILED_QUESTION_NOT_EXISTS);
        }

        return BeanUtil.copyProperties(question, QuestionSearchVo.class);
    }

    // 修改
    public int update(QuestionUpdateDto questionUpdateDto) {
        Question question = questionMapper.selectById(questionUpdateDto.getQuestionId());
        if(question == null){
            throw new ServiceException(ResCode.FAILED_QUESTION_NOT_EXISTS);
        }

        question.setTitle(questionUpdateDto.getTitle());
        question.setContent(questionUpdateDto.getContent());
        question.setQuestionCase(questionUpdateDto.getQuestionCase());
        question.setDefaultCode(questionUpdateDto.getDefaultCode());
        question.setMain(questionUpdateDto.getMain());
        question.setDifficulty(questionUpdateDto.getDifficulty());
        question.setSpaceLimit(questionUpdateDto.getSpaceLimit());
        question.setTimeLimit(questionUpdateDto.getTimeLimit());
        return questionMapper.updateById(question);
    }

    // 删除
    public int delete(Long questionId){
        return questionMapper.deleteById(questionId);
    }
}
