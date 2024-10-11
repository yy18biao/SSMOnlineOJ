package com.hjb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjb.core.domain.Resp;
import com.hjb.core.enums.ResCode;
import com.hjb.core.enums.UserIdentity;
import com.hjb.core.utils.BCryptUtils;
import com.hjb.security.exception.ServiceException;
import com.hjb.security.service.TokenService;
import com.hjb.system.domain.admin.Admin;
import com.hjb.system.domain.admin.DTO.AdminAddDTO;
import com.hjb.system.mapper.AdminMapper;
import com.hjb.system.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
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

    @Override
    public int add(AdminAddDTO adminAddDTO) {
        // 判断是否已经存在该用户
        Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUserId, adminAddDTO.getUserId()));
        if(admin != null){
            // 自定义异常
            throw new ServiceException(ResCode.USER_EXISTS);
        }

        Admin newAdmin = new Admin();
        newAdmin.setNickname(adminAddDTO.getNickname());
        newAdmin.setPassword(BCryptUtils.encrypt(adminAddDTO.getPassword()));
        newAdmin.setUserId(adminAddDTO.getUserId());

        return adminMapper.insert(admin);
    }
}
