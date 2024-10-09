package com.hjb.security;

import com.hjb.core.domain.Resp;
import com.hjb.core.enums.ResCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    // 请求方法不支持
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Resp<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Resp.fail(ResCode.ERROR);
    }

    // 运行时异常
    @ExceptionHandler(RuntimeException.class)
    public Resp<?> handleRuntimeException(RuntimeException e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生运行时异常.", requestURI, e);
        return Resp.fail(ResCode.ERROR);
    }

    // 系统的异常
    @ExceptionHandler(Exception.class)
    public Resp<?> handleException(Exception e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生异常.", requestURI, e);
        return Resp.fail(ResCode.ERROR);
    }
}
