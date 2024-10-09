package com.hjb.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hjb.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("admin")
public class Admin extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String userId;
    private String password;
    private String nickname;
}
