package com.hjb.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.admin.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
