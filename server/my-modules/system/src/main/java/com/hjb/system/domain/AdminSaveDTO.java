package com.hjb.system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminSaveDTO extends AdminDTO {
    @Schema(description = "管理员昵称")
    private String nickname;
}
