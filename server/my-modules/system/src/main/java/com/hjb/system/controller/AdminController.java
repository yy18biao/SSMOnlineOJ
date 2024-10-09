package com.hjb.system.controller;

import com.hjb.core.domain.Resp;
import com.hjb.system.domain.AdminDTO;
import com.hjb.system.domain.AdminSaveDTO;
import com.hjb.system.domain.AdminVO;
import com.hjb.system.service.impl.AdminServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@Tag(name = "管理员相关接口")
public class AdminController {
    @Resource
    private AdminServiceImpl adminServiceImpl;

    @PostMapping("/login")
    @Operation(summary = "管理员登录验证接口")
    @ApiResponse(responseCode = "200", description = "登录成功")
    @ApiResponse(responseCode = "2101", description = "用户不存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    @ApiResponse(responseCode = "2102", description = "账号/密码错误")
    public Resp<String> login(@RequestBody AdminDTO adminDTO) {
        Resp<String> resp = new Resp<>();
        resp = adminServiceImpl.login(adminDTO.getUserId(), adminDTO.getPassword());
        return resp;
    }

    @PostMapping("add")
    @Operation(summary = "新增管理员接口")
    @ApiResponse(responseCode = "200", description = "新增成功")
    @ApiResponse(responseCode = "2100", description = "用户已存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<String> add(@RequestBody AdminSaveDTO adminSaveDTO) {
        return null;
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "删除管理员接口")
    @Parameter(name = "userId", in = ParameterIn.PATH, description = "管理员账号")
    @ApiResponse(responseCode = "200", description = "删除成功")
    @ApiResponse(responseCode = "2101", description = "用户不存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<String> delete(@PathVariable Long userId) {
        return null;
    }

    @GetMapping("/getAdmin")
    @Operation(summary = "获取管理员信息接口")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.QUERY, description = "管理员账号")
    })
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2101", description = "用户不存在")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<AdminVO> getAdmin(@RequestParam(required = true) Long userId) {
        return null;
    }
}
