package com.hjb.security.exception;

import com.hjb.core.enums.ResCode;

// 业务处理服务层发现的异常

public class ServiceException extends RuntimeException {
    private ResCode resCode;

    public ServiceException(ResCode resCode) {
        this.resCode = resCode;
    }

    public ResCode getResCode() {
        return resCode;
    }
}
