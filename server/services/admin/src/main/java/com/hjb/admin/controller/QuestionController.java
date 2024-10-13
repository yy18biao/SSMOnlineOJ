package com.hjb.admin.controller;

import com.hjb.admin.domain.question.QuestionAddDto;
import com.hjb.admin.domain.question.QuestionQueryDto;
import com.hjb.admin.domain.question.QuestionSearchVo;
import com.hjb.admin.domain.question.QuestionUpdateDto;
import com.hjb.admin.service.QuestionService;
import com.hjb.core.controller.BaseController;
import com.hjb.core.domain.Resp;
import com.hjb.core.domain.TableData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public TableData list(QuestionQueryDto questionQueryDto){
        return getTableData(questionService.list(questionQueryDto));
    }

    @PostMapping("/add")
    @Operation(summary = "添加题目")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> add(@Validated @RequestBody QuestionAddDto questionAddDto){
        return questionService.add(questionAddDto) > 0 ? Resp.ok() : Resp.fail();
    }

    @GetMapping("/search")
    @Operation(summary = "获取题目详情")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<QuestionSearchVo> search(Long questionId){
        return Resp.ok(questionService.search(questionId));
    }

    @PutMapping("/update")
    @Operation(summary = "修改题目")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> update(@Validated @RequestBody QuestionUpdateDto questionUpdateDto){
        return questionService.update(questionUpdateDto) > 0 ? Resp.ok() : Resp.fail();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> delete(Long questionId){
        return questionService.delete(questionId) > 0 ? Resp.ok() : Resp.fail();
    }
}
