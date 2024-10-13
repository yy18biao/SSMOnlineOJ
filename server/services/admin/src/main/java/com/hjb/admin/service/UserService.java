package com.hjb.admin.service;

import com.github.pagehelper.PageHelper;
import com.hjb.admin.domain.user.User;
import com.hjb.admin.domain.user.UserDto;
import com.hjb.admin.domain.user.UserQueryDto;
import com.hjb.admin.domain.user.UserVo;
import com.hjb.admin.mapper.UserMapper;
import com.hjb.core.domain.Resp;
import com.hjb.core.enums.ResCode;
import com.hjb.security.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<UserVo> list(UserQueryDto userQueryDto){
        // 分页
        PageHelper.startPage(userQueryDto.getPageNum(), userQueryDto.getPageSize());
        return userMapper.list(userQueryDto);
    }

    public int updateStatus(UserDto userDto){
        User user = userMapper.selectById(userDto.getUserId());
        if (user == null) {
            throw new ServiceException(ResCode.USER_NOT_EXISTS);
        }
        user.setStatus(userDto.getStatus());
        return userMapper.updateById(user);
    }
}
