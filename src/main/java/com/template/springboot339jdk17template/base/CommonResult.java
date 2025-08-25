package com.template.springboot339jdk17template.base;


import com.template.springboot339jdk17template.enums.BizCodeEnum;
import com.template.springboot339jdk17template.enums.IErrorCode;
import lombok.Data;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
@Data
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;
    private String requestId;
    protected CommonResult() {
    }

    protected CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), data);
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> successByRebateSum(T data) {
        return new CommonResult<T>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功结果
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(BizCodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 成功返回结果
     * @param errorCode
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> success(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message) {
        return new CommonResult<T>(errorCode.getCode(), message, null);
    }

    public static <T> CommonResult<T> failed(BizCodeEnum bizCodeEnum,T data) {
        return new CommonResult<T>(bizCodeEnum.getCode(), bizCodeEnum.getMessage(), data);
    }

    public static <T> CommonResult<T> failed(BizCodeEnum bizCodeEnum,String message) {
        return new CommonResult<T>(bizCodeEnum.getCode(), message, null);
    }

    public static <T> CommonResult<T> failed(int errorCode,String message) {
        return new CommonResult<T>(errorCode, message, null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     * @param data 错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message, T data) {
        return new CommonResult<T>(errorCode.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(BizCodeEnum.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(BizCodeEnum.FAILED);
    }
}
