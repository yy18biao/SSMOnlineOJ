package com.hjb.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResCode {
    SUCCESS(200, "操作成功"),
    FAILED(300, "操作失败"),
    ERROR(2000, "服务器繁忙"),
    USER_EXISTS(2100, "用户已存在"),
    USER_NOT_EXISTS(2101, "用户不存在"),
    FAILED_LOGIN(2102, "账号/密码错误"),

    FAILED_UNAUTHORIZED(3000, "未授权"),
    FAILED_PARAMS_VALIDATE(3001, "参数校验失败"),

    FAILED_QUESTION_EXISTS(3100, "题目已存在"),
    FAILED_QUESTION_NOT_EXISTS(3101, "题目不存在"),

    FAILED_EXAM_EXISTS(3200, "竞赛已存在"),
    FAILED_EXAM_NOT_EXISTS(3201, "竞赛不存在"),
    EXAM_START_TIME_BEFORE_CURRENT_TIME(3202, "竞赛的开始时间不能早于当前时间"),
    EXAM_START_TIME_AFTER_END_TIME(3203, "竞赛的结束时间不能早于开始时间"),
    EXAM_STARTED(3204, "竞赛已开始"),
    EXAM_IS_PUBLISH(3205, "竞赛处于发布状态"),
    EXAM_QUESTION_NOT_EXISTS(3206, "竞赛题目不存在"),
    EXAM_NOT_HAS_QUESTION(3207, "竞赛中没有题目"),

    FAILED_PHONE(3300, "手机号码格式错误"),
    PHONE_EXISTS(3301, "手机号已被注册"),
    PASSWORD_NO_PASSWORD(3302, "两次密码不一致"),
    FAILED_CODE(3303, "验证码错误");

    private int code;
    private String msg;
}
