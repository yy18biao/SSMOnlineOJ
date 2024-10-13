package com.hjb.admin.domain.exam;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjb.core.domain.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("exam")
public class Exam extends BaseEntity {
    @TableId(value = "examId", type = IdType.ASSIGN_ID)
    private Long examId;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer status;
}
