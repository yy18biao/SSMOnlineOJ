package com.hjb.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.hjb.admin.domain.exam.ExamAddDto;
import com.hjb.admin.domain.exam.ExamQueryDto;
import com.hjb.admin.domain.exam.ExamSearchVo;
import com.hjb.admin.domain.exam.ExamVo;
import com.hjb.admin.domain.exam_question.ExamAddQuestionDto;
import com.hjb.admin.domain.exam_question.ExamDeleteQuestionDto;
import com.hjb.admin.service.ExamService;
import com.hjb.core.controller.BaseController;
import com.hjb.core.domain.Resp;
import com.hjb.core.domain.TableData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {
    @Resource
    private ExamService examService;

    @GetMapping("/list")
    @Operation(summary = "获取竞赛列表")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public TableData list(ExamQueryDto examQueryDto) {
        return getTableData(examService.list(examQueryDto));
    }

    @PostMapping("/addExam")
    @Operation(summary = "新增竞赛")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<String> add(@RequestBody ExamAddDto examAddDto) {
        String res = examService.addExam(examAddDto);
        return res.isEmpty() ? Resp.fail() : Resp.ok(res);
    }

    @PostMapping("/addQuestion")
    @Operation(summary = "新增题目")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> addQuestion(@RequestBody ExamAddQuestionDto examAddQuestionDto) {
        return examService.addQuestion(examAddQuestionDto) ? Resp.ok() : Resp.fail();
    }

    @DeleteMapping("/deleteQuestion")
    @Operation(summary = "删除题目")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> deleteQuestion(Long examId, Long questionId) {
        return examService.deleteQuestion(examId, questionId) > 0 ? Resp.ok() : Resp.fail();
    }

    @GetMapping("/search")
    @Operation(summary = "获取竞赛信息")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<ExamSearchVo> search(Long examId) {
        return Resp.ok(examService.search(examId));
    }

    @PutMapping("/publishExam")
    @Operation(summary = "发布竞赛")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> publishExam(Long examId) {
        return examService.publishExam(examId) > 0 ? Resp.ok() : Resp.fail();
    }

    @PutMapping("/cancelExam")
    @Operation(summary = "发布竞赛")
    @ApiResponse(responseCode = "200", description = "获取成功")
    @ApiResponse(responseCode = "2000", description = "服务器繁忙")
    public Resp<Void> cancelExam(Long examId) {
        return examService.cancelExam(examId) > 0 ? Resp.ok() : Resp.fail();
    }
}
