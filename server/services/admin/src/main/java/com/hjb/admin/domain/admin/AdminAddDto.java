package com.hjb.admin.domain.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminAddDto extends AdminDto {
    @Schema(description = "管理员昵称")
    private String nickname;
}
