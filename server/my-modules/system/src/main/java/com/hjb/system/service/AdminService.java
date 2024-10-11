package com.hjb.system.service;


import com.hjb.core.domain.Resp;
import com.hjb.system.domain.admin.DTO.AdminAddDTO;

public interface AdminService {
    Resp<String> login(String userId, String password);

    int add(AdminAddDTO adminAddDTO);
}
