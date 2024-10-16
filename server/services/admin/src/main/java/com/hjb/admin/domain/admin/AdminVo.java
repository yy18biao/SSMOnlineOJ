package com.hjb.admin.domain.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminVo {
    @Schema(description = "管理员账号")
    private String userId;

    @Schema(description = "管理员昵称")
    private String nickname;
}
