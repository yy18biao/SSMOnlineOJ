package com.hjb.system.domain.admin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AdminAddDTO extends AdminDTO {
    @Schema(description = "管理员昵称")
    private String nickname;
}
