package com.hjb.admin.domain.exam_question;

import lombok.Data;

@Data
public class ExamDeleteQuestionDto {
    private Long examId;
    private Long questionId;
}
