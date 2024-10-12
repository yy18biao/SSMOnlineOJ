package com.hjb.admin.domain.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

// 题目的响应对象
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String title;

    private Integer difficulty;

    private String createName; // 创建人的昵称

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
