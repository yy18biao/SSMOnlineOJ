package com.hjb.admin.domain.exam_question;

import lombok.Data;

import java.util.LinkedHashSet;

@Data
public class ExamAddQuestionDto {
    private Long examId;
    private LinkedHashSet<Long> questionIds;
}
