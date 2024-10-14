package com.hjb.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
