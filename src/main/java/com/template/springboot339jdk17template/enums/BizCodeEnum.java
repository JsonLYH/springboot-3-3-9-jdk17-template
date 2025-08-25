package com.template.springboot339jdk17template.enums;

import lombok.ToString;


@ToString
public enum BizCodeEnum implements IErrorCode{
    /***********************通用响应格式************************/
    SUCCESS(200, "操作成功"),
    FAILED(224201, "业务繁忙，请稍后再试~"),
    BUSINESS_BUSY(224201,"业务繁忙，请稍后再试~"),
    REQUEST_TOOL_FAST(224200,"请求太频繁啦~") ,
    SCAN_TOOL_FAST_WAIT_TRY(224222,"扫描太频繁，请稍后再试~") ,
    SYSTEMCTL_ERROR(224500,"系统异常，请稍后再试~");

    /********************************************************/

    private int code;
    private String message;

    BizCodeEnum(String message) {
        this.message = message;
    }

    private BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
