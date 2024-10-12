package com.hjb.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.hjb.admin.domain.exam.Exam;
import com.hjb.admin.domain.exam.ExamAddDto;
import com.hjb.admin.domain.exam.ExamQueryDto;
import com.hjb.admin.domain.exam.ExamVo;
import com.hjb.admin.domain.exam_question.ExamAddQuestionDto;
import com.hjb.admin.domain.exam_question.ExamQuestion;
import com.hjb.admin.domain.question.Question;
import com.hjb.admin.mapper.ExamMapper;
import com.hjb.admin.mapper.ExamQuestionMapper;
import com.hjb.admin.mapper.QuestionMapper;
import com.hjb.core.enums.ResCode;
import com.hjb.security.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService extends ServiceImpl<ExamQuestionMapper, ExamQuestion> {
    @Resource
    private ExamMapper examMapper;

    @Resource
    private QuestionMapper questionMapper;

    // 判断竞赛是否重复和时间是否符合逻辑
    private void checkExam(ExamAddDto examAddDto, Long id) {
        // 判断重复性
        List<Exam> examList = examMapper.selectList(new LambdaQueryWrapper<Exam>()
                .eq(Exam::getTitle, examAddDto.getTitle())
                .ne(id != null, Exam::getId, id));
        if(CollectionUtil.isNotEmpty(examList)){
            throw new ServiceException(ResCode.FAILED_EXAM_EXISTS);
        }

        // 判断开始时间是否在当前时间之前
        if (examAddDto.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResCode.EXAM_START_TIME_BEFORE_CURRENT_TIME);
        }

        // 判断结束时间是否在开始时间之前
        if (examAddDto.getEndTime().isBefore(examAddDto.getStartTime())) {
            throw new ServiceException(ResCode.EXAM_START_TIME_AFTER_END_TIME);
        }
    }

    // 检查竞赛是否合理（判断竞赛是否已经开始）
    private void checkExamIsStart(Exam exam){
        if (exam.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResCode.EXAM_STARTED);
        }
    }

    public List<ExamVo> list(ExamQueryDto examQueryDto) {
        // 分页
        PageHelper.startPage(examQueryDto.getPageNum(), examQueryDto.getPageSize());
        return examMapper.list(examQueryDto);
    }

    // 添加竞赛
    public String addExam(ExamAddDto examAddDto) {
        // 判断合理性
        checkExam(examAddDto, null);

        Exam exam = BeanUtil.copyProperties(examAddDto, Exam.class);
        if(examMapper.insert(exam) < 1)
            return "";
        return exam.getId().toString();
    }

    // 添加题目
    public boolean addQuestion(ExamAddQuestionDto examAddQuestionDto) {
        // 判断题目id列表是否为空
        if(CollectionUtil.isEmpty(examAddQuestionDto.getQuestionIds())){
            return true;
        }

        Exam exam = examMapper.selectById(examAddQuestionDto.getExamId());

        // 检查竞赛是否合理（判断竞赛是否已经开始）
        checkExamIsStart(exam);
        // 判断竞赛是否处于未发布状态
        if (exam.getStatus() == 1) {
            throw new ServiceException(ResCode.EXAM_IS_PUBLISH);
        }

        // 获取题目列表
        // 使用mybatis-plus中的selectBatchIds方法批量查询出数据，避免多次循环访问数据库
        List<Question> questions = questionMapper.selectBatchIds(examAddQuestionDto.getQuestionIds());
        if(CollectionUtil.isEmpty(questions) || questions.size() < examAddQuestionDto.getQuestionIds().size()){
            throw new ServiceException(ResCode.EXAM_QUESTION_NOT_EXISTS);
        }

        // 保存题目id和竞赛的关联
        int order = 1; // 题目顺序
        List<ExamQuestion> examQuestions = new ArrayList<>();
        for (Long questionId : examAddQuestionDto.getQuestionIds()) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExamId(exam.getId());
            examQuestion.setQuestionId(questionId);
            examQuestion.setQuestionOrder(order++);
            examQuestions.add(examQuestion);
        }
        // 使用mybatis-plus中的saveBatch批量插入，避免多次循环访问数据库
        return saveBatch(examQuestions);
    }
}
