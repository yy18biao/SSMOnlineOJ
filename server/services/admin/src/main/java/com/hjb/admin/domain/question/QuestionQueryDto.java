package com.hjb.admin.domain.question;

import com.hjb.core.domain.PageQueryDto;
import lombok.Data;

import java.util.Set;

// 题目查询相关请求对象
@Data
public class QuestionQueryDto extends PageQueryDto {
    private Integer difficulty; // 难度

    private String title; // 标题

    private Set<Long> excludeIdSet; // 排除的题目id集合
}
