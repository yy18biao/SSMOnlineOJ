package com.hjb.system.domain.question.DTO;

import com.hjb.core.domain.PageQueryDTO;
import lombok.Data;

import java.util.Set;

// 题目查询相关请求对象
@Data
public class QuestionQueryDTO extends PageQueryDTO {
    private Integer difficulty; // 难度

    private String title; // 标题

    private Set<Long> excludeIdSet; // 排除的题目id集合
}
