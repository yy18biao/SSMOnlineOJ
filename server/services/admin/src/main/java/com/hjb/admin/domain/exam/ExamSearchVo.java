package com.hjb.admin.domain.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hjb.admin.domain.question.QuestionVo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamSearchVo {
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private List<QuestionVo> examQuestionList;
}
