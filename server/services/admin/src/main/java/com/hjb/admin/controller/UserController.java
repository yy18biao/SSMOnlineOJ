package com.hjb.admin.controller;

import com.hjb.admin.domain.user.UserDto;
import com.hjb.admin.domain.user.UserQueryDto;
import com.hjb.admin.service.UserService;
import com.hjb.core.controller.BaseController;
import com.hjb.core.domain.Resp;
import com.hjb.core.domain.TableData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理相关接口")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public TableData list(UserQueryDto userQueryDto) {
        return getTableData(userService.list(userQueryDto));
    }

    @PutMapping("/updateStatus")
    public Resp<Void> updateStatus(@RequestBody UserDto userDto) {
        return userService.updateStatus(userDto) > 0 ? Resp.ok() : Resp.fail();
    }
}
