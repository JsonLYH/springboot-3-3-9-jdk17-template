package com.template.springboot339jdk17template.exception;

import com.template.springboot339jdk17template.enums.BizCodeEnum;
import lombok.Data;

@Data
public class BizException extends RuntimeException {
    private int code;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BizException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BizException(String message) {
        super(message);
        this.code=500;
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
    }

}
