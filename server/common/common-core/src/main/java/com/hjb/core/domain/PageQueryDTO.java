package com.hjb.core.domain;

import lombok.Data;

@Data
public class PageQueryDTO {
    private Integer pageSize = 10;  // 每页的数据

    private Integer pageNum = 1; // 第几页
}
