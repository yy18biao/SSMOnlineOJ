package com.hjb.system.service;

import com.hjb.system.domain.question.DTO.QuestionQueryDTO;
import com.hjb.system.domain.question.VO.QuestionVO;

import java.util.List;


public interface QuestionService {
    List<QuestionVO> list(QuestionQueryDTO questionQueryDTO);
}
