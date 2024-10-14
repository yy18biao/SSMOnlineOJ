package com.hjb.user.domain.dto;

import lombok.Data;

@Data
public class UserAddDto {
    private String phone;
    private String password;
    private String code;
}
