package com.hjb.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.admin.domain.question.Question;
import com.hjb.admin.domain.question.QuestionQueryDto;
import com.hjb.admin.domain.question.QuestionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    List<QuestionVo> selectQuestionList(QuestionQueryDto questionQueryDto);
}
