package com.liangxq.callback;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq.callback
 * 文件名：BaseResponce
 * 创建者：liangxq
 * 创建时间：2019/4/29  9:33
 * 描述：TODO
 */
public class BaseResponce<T> {
    String errorMsg;
    int errorCode;
    T data;
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = data;
    }
}
