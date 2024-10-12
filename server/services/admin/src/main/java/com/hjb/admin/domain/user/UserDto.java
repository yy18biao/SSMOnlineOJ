package com.hjb.admin.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @Schema(description = "管理员账号")
    @NotNull(message = "账号不能为null")
    private String userId;
    @Schema(description = "管理员密码")
    @NotNull(message = "密码不能为null")
    private String password;
}
