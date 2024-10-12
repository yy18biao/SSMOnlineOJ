package com.hjb.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.admin.domain.exam.Exam;
import com.hjb.admin.domain.exam.ExamQueryDto;
import com.hjb.admin.domain.exam.ExamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamMapper extends BaseMapper<Exam> {
    List<ExamVo> list(ExamQueryDto examQueryDto);
}
