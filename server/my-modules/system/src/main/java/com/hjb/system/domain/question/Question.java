package com.hjb.system.domain.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjb.core.domain.BaseEntity;
import lombok.Data;

@TableName("question")
@Data
public class Question extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String main;
}
