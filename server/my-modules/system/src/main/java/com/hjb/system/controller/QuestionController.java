package com.hjb.system.controller;

import com.hjb.core.controller.BaseController;
import com.hjb.core.domain.TableData;
import com.hjb.system.domain.question.DTO.QuestionQueryDTO;
import com.hjb.system.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
@Tag(name = "题目管理接口")
public class QuestionController extends BaseController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/list")
    @Operation(summary = "获取题目列表")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public TableData list(QuestionQueryDTO questionQueryDTO){
        return getTableData(questionService.list(questionQueryDTO));
    }
}
