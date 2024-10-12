package com.hjb.admin.controller;

import com.hjb.admin.domain.user.UserAddDto;
import com.hjb.admin.domain.user.UserDto;
import com.hjb.admin.service.UserService;
import com.hjb.core.constants.HttpConstants;
import com.hjb.core.domain.Resp;
import com.hjb.core.domain.vo.LoginUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "管理员相关接口")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录验证接口")
    @ApiResponse(responseCode = "200", description = "登录成功")
    @ApiResponse(responseCode = "2101", description = "用户不存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    @ApiResponse(responseCode = "2102", description = "账号/密码错误")
    public Resp<String> login(@Validated @RequestBody UserDto userDto) {
        return userService.login(userDto.getUserId(), userDto.getPassword());
    }

    @PostMapping("/add")
    @Operation(summary = "新增管理员接口")
    @ApiResponse(responseCode = "200", description = "新增成功")
    @ApiResponse(responseCode = "2100", description = "用户已存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> add(@RequestBody UserAddDto userAddDto) {
        return userService.add(userAddDto) > 0 ? Resp.ok() : Resp.fail();
    }

    @GetMapping("/getUser")
    @Operation(summary = "获取管理员信息接口")
    @Parameter(name = "token", in = ParameterIn.COOKIE, description = "token")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2101", description = "用户不存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<LoginUserVO> getUser(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return userService.getUser(token);
    }

    @DeleteMapping("/logout")
    @Operation(summary = "退出管理员登录")
    @Parameter(name = "token", in = ParameterIn.COOKIE, description = "token")
    @ApiResponse(responseCode = "200", description = "退出成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> logout(@RequestHeader(HttpConstants.AUTHENTICATION) String token) {
        return userService.logout(token) ? Resp.ok() : Resp.fail();
    }
}
