package com.hjb.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
}
