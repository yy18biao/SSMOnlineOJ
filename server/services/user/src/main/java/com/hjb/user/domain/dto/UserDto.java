package com.hjb.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String phone;
    private String password;
    private String code;
}
