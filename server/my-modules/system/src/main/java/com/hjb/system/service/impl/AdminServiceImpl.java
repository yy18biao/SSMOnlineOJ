package com.hjb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjb.core.domain.Resp;
import com.hjb.core.enums.ResCode;
import com.hjb.core.enums.UserIdentity;
import com.hjb.core.utils.BCryptUtils;
import com.hjb.security.service.TokenService;
import com.hjb.system.domain.admin.Admin;
import com.hjb.system.mapper.AdminMapper;
import com.hjb.system.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Resp<String> login(String userId, String password) {
        // 通过账号去数据库中查询对应用户信息
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        Admin admin = adminMapper.selectOne(queryWrapper
                .select(Admin::getId, Admin::getPassword, Admin::getNickname)
                .eq(Admin::getUserId, userId));

        if(admin == null){
            return Resp.fail(ResCode.USER_NOT_EXISTS);
        }

        if(!BCryptUtils.matches(password, admin.getPassword())){
            return Resp.fail(ResCode.FAILED_LOGIN);
        }

        // 生成token并返回
        return Resp.ok(tokenService.createToken(userId, secret, UserIdentity.ADMIN.getValue()));
    }
}
