package com.liangxq;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq
 * 文件名：ApiException
 * 创建者：liangxq
 * 创建时间：2019/4/29  9:22
 * 描述：TODO
 */
public class ApiException extends Throwable{

    private String message;

    private int code;

    public ApiException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
