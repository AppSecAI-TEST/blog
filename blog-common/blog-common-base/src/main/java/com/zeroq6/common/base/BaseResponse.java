package com.zeroq6.common.base;

import java.io.Serializable;

/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private T body;
    public BaseResponse(){}

    public BaseResponse(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
