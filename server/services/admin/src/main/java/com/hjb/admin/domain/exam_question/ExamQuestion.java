package com.hjb.admin.domain.exam_question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjb.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("question_exam")
public class ExamQuestion extends BaseEntity {
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    private Long examId;

    private Long questionId;

    private Integer questionOrder;
}
