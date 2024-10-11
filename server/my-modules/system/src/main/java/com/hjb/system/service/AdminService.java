package com.hjb.system.service;


import com.hjb.core.domain.Resp;
import com.hjb.core.domain.vo.LoginUserVO;
import com.hjb.system.domain.admin.DTO.AdminAddDTO;
import com.hjb.system.domain.admin.VO.AdminVO;

public interface AdminService {
    Resp<String> login(String userId, String password);

    int add(AdminAddDTO adminAddDTO);

    Resp<LoginUserVO> getAdmin(String token);

    boolean logout(String token);
}
