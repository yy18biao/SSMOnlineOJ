package com.hjb.admin.service;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjb.admin.domain.admin.Admin;
import com.hjb.admin.domain.admin.AdminAddDto;
import com.hjb.admin.mapper.AdminMapper;
import com.hjb.core.constants.HttpConstants;
import com.hjb.core.domain.LoginUserData;
import com.hjb.core.domain.Resp;
import com.hjb.core.domain.vo.LoginUserVO;
import com.hjb.core.enums.ResCode;
import com.hjb.core.enums.UserIdentity;
import com.hjb.core.utils.BCryptUtils;
import com.hjb.security.exception.ServiceException;
import com.hjb.security.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;
    
    public Resp<String> login(String userId, String password) {
        // 通过账号去数据库中查询对应用户信息
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
        return Resp.ok(tokenService.createToken(userId, secret, UserIdentity.ADMIN.getValue(), admin.getNickname()));
    }
    
    public int add(AdminAddDto adminAddDto) {
        // 判断是否已经存在该用户
        Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUserId, adminAddDto.getUserId()));
        if(admin != null){
            // 自定义异常
            throw new ServiceException(ResCode.USER_EXISTS);
        }

        Admin newAdmin = new Admin();
        newAdmin.setNickname(adminAddDto.getNickname());
        newAdmin.setPassword(BCryptUtils.encrypt(adminAddDto.getPassword()));
        newAdmin.setUserId(adminAddDto.getUserId());

        return adminMapper.insert(admin);
    }
    
    public Resp<LoginUserVO> getUser(String token) {
        // 判断token合理性和去除token前缀
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, StrUtil.EMPTY);
        }

        // 获取用户信息
        LoginUserData loginUserData = tokenService.getLoginUser(token, secret);
        if(loginUserData == null){
            return Resp.fail();
        }

        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setNickname(loginUserData.getNickname());
        return Resp.ok(loginUserVO);
    }
    
    public boolean logout(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, StrUtil.EMPTY);
        }

        return tokenService.deleteLoginUser(token, secret);
    }
}
