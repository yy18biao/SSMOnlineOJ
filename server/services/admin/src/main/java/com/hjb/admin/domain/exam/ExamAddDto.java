package com.hjb.admin.domain.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hjb.core.domain.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamAddDto {
    @NotNull(message = "标题不能为null")
    private String title;

    @NotNull(message = "开始时间不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "结束时间不能为null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
