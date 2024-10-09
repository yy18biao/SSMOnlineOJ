package com.hjb.system.service;


import com.hjb.core.domain.Resp;

public interface AdminService {
    Resp<String> login(String userId, String password);
}
