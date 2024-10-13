package com.hjb.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjb.admin.domain.exam.ExamQueryDto;
import com.hjb.admin.domain.exam.ExamVo;
import com.hjb.admin.domain.user.User;
import com.hjb.admin.domain.user.UserQueryDto;
import com.hjb.admin.domain.user.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserVo> list(UserQueryDto userQueryDto);
}
