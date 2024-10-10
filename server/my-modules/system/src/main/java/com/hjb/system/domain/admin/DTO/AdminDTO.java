package com.hjb.system.domain.admin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminDTO {
    @Schema(description = "管理员账号")
    private String userId;
    @Schema(description = "管理员密码")
    private String password;
}
