package com.hjb.admin.domain.user;

import com.hjb.core.domain.PageQueryDto;
import lombok.Data;

@Data
public class UserQueryDto extends PageQueryDto {
    private String userId;
    private String nickname;
}
