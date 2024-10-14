package com.hjb.user.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjb.core.constants.RedisConstants;
import com.hjb.core.domain.Resp;
import com.hjb.core.enums.ResCode;
import com.hjb.core.enums.UserIdentity;
import com.hjb.core.utils.BCryptUtils;
import com.hjb.redis.service.RedisService;
import com.hjb.security.exception.ServiceException;
import com.hjb.security.service.TokenService;
import com.hjb.sms.SMSService;
import com.hjb.user.domain.User;
import com.hjb.user.domain.dto.UserAddDto;
import com.hjb.user.domain.dto.UserDto;
import com.hjb.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SMSService smsService;

    @Resource
    private RedisService redisService;

    @Resource
    private TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;

    // 判断手机合理性
    public static boolean checkPhone(String phone) {
        Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
        Matcher m = regex.matcher(phone);
        return m.matches();
    }

    // 发送注册验证码
    public boolean sendRegCode(UserDto userDto) {
        if (!checkPhone(userDto.getPhone())) {
            throw new ServiceException(ResCode.FAILED_PHONE);
        }

        String code = RandomUtil.randomNumbers(6);
        String phone = userDto.getPhone();

        // 存储到redis中 有效时间5分钟
        redisService.set(RedisConstants.REG_PHONE_CODE_KEY + phone, code, 5L, TimeUnit.MINUTES);

        return smsService.sendPhoneCode(userDto.getPhone(), code);
    }

    // 发送登录验证码
    public boolean sendLoginCode(UserDto userDto) {
        if (!checkPhone(userDto.getPhone())) {
            throw new ServiceException(ResCode.FAILED_PHONE);
        }

        String code = RandomUtil.randomNumbers(6);
        String phone = userDto.getPhone();

        // 存储到redis中 有效时间5分钟
        redisService.set(RedisConstants.LOGIN_PHONE_CODE_KEY + phone, code, 5L, TimeUnit.MINUTES);

        return smsService.sendPhoneCode(userDto.getPhone(), code);
    }

    // 注册
    public int reg(UserAddDto userAddDto) {
        // 判断手机号码是否合理
        if (!checkPhone(userAddDto.getPhone())) {
            throw new ServiceException(ResCode.FAILED_PHONE);
        }

        // 判断用户是否已经存在
        if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getPhone, userAddDto.getPhone())) > 0) {
            throw new ServiceException(ResCode.PHONE_EXISTS);
        }

        // 判断验证码是否正确
        String code = redisService.get(RedisConstants.REG_PHONE_CODE_KEY + userAddDto.getPhone(), String.class);
        if(!code.equals(userAddDto.getCode())){
            throw new ServiceException(ResCode.FAILED_CODE);
        }

        User user = BeanUtil.copyProperties(userAddDto, User.class);
        user.setPassword(BCryptUtils.encrypt(user.getPassword()));
        return userMapper.insert(user);
    }

    // 密码登录
    public String passLogin(String phone, String password) {
        // 判断手机号码是否合理
        if (!checkPhone(phone)) {
            throw new ServiceException(ResCode.FAILED_PHONE);
        }

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if(user == null){
            throw new ServiceException(ResCode.USER_NOT_EXISTS);
        }

        if(!BCryptUtils.matches(password, user.getPassword())){
            throw new ServiceException(ResCode.FAILED_LOGIN);
        }

        return tokenService.createToken(user.getUserId().toString(), secret, UserIdentity.ORDINARY.getValue(), user.getNickname(), user.getPhoto());
    }

    // 验证码登录
    public String codeLogin(String phone, String code) {
        // 判断手机号码是否合理
        if (!checkPhone(phone)) {
            throw new ServiceException(ResCode.FAILED_PHONE);
        }

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if(user == null){
            throw new ServiceException(ResCode.USER_NOT_EXISTS);
        }

        if(!code.equals(redisService.get(RedisConstants.LOGIN_PHONE_CODE_KEY + phone, String.class))){
            throw new ServiceException(ResCode.FAILED_CODE);
        }

        return tokenService.createToken(user.getUserId().toString(), secret, UserIdentity.ORDINARY.getValue(), user.getNickname(), user.getPhoto());
    }
}
