package com.hjb.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.hjb.system.domain.question.DTO.QuestionQueryDTO;
import com.hjb.system.domain.question.VO.QuestionVO;
import com.hjb.system.mapper.QuestionMapper;
import com.hjb.system.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionVO> list(QuestionQueryDTO questionQueryDTO) {
        // 分页
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());
        return questionMapper.selectQuestionList(questionQueryDTO);
    }
}
