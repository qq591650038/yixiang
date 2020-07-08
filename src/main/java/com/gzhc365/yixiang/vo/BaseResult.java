package com.gzhc365.yixiang.vo;

import java.io.Serializable;

/**
 * @author: qq895
 * @date: 2020/7/1 16:00
 * @description:
 */
public class BaseResult<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public BaseResult() {
        this.code = "0";
        this.msg = "成功";
    }

    public BaseResult(T data) {
        this.code = "0";
        this.msg = "成功";
        this.data = data;
    }
    

    public BaseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
