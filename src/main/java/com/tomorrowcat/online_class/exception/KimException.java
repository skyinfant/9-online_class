package com.tomorrowcat.online_class.exception;

/**
 * @description: 自定义异常
 * @author: kim
 * @create: 2021-03-27 20:02
 * @version: 1.0.0
 */
public class KimException extends RuntimeException {
    private Integer code;

    private String msg;

    public KimException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}