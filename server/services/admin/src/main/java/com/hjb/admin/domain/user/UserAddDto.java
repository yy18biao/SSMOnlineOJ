package com.hjb.admin.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserAddDto extends UserDto {
    @Schema(description = "管理员昵称")
    private String nickname;
}
