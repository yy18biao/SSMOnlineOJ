package com.hjb.admin.domain.question;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionAddDto {
    @NotNull(message = "标题不能为null")
    private String title;

    @NotNull(message = "题目难度不能为null")
    private Integer difficulty;

    @NotNull(message = "时间限制不能为null")
    private Long timeLimit;

    @NotNull(message = "空间限制不能为null")
    private Long spaceLimit;

    @NotNull(message = "题目内容不能为null")
    private String content;

    @NotNull(message = "题目用例不能为null")
    private String questionCase;

    @NotNull(message = "默认代码块不能为null")
    private String defaultCode;

    @NotNull(message = "main函数块不能为null")
    private String main;
}
