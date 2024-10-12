package com.hjb.admin.service;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjb.admin.domain.user.User;
import com.hjb.admin.domain.user.UserAddDto;
import com.hjb.admin.mapper.UserMapper;
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
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;
    
    public Resp<String> login(String userId, String password) {
        // 通过账号去数据库中查询对应用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId, User::getPassword, User::getNickname)
                .eq(User::getUserId, userId));

        if(user == null){
            return Resp.fail(ResCode.USER_NOT_EXISTS);
        }

        if(!BCryptUtils.matches(password, user.getPassword())){
            return Resp.fail(ResCode.FAILED_LOGIN);
        }

        // 生成token并返回
        return Resp.ok(tokenService.createToken(userId, secret, UserIdentity.ADMIN.getValue(), user.getNickname()));
    }
    
    public int add(UserAddDto userAddDto) {
        // 判断是否已经存在该用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, userAddDto.getUserId()));
        if(user != null){
            // 自定义异常
            throw new ServiceException(ResCode.USER_EXISTS);
        }

        User newUser = new User();
        newUser.setNickname(userAddDto.getNickname());
        newUser.setPassword(BCryptUtils.encrypt(userAddDto.getPassword()));
        newUser.setUserId(userAddDto.getUserId());

        return userMapper.insert(user);
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
