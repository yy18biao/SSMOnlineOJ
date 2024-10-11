package com.hjb.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.system.domain.question.DTO.QuestionQueryDTO;
import com.hjb.system.domain.question.Question;
import com.hjb.system.domain.question.VO.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    List<QuestionVO> selectQuestionList(QuestionQueryDTO questionQueryDTO);
}
