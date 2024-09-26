package com.canbro.coffee.pojo.vo;

import com.canbro.coffee.consts.HttpStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Http结果类
 * @param
 *
 * @author A-pppp
 */
@Setter
@Getter
public class RetVo {
    private int code;
    private String msg;
    private Object data;

    public RetVo() {}

    public RetVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RetVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RetVo success() {
        return new RetVo(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMsg());
    }

    public static RetVo success(String msg) {
        return new RetVo(HttpStatus.SUCCESS.getCode(), msg);
    }

    public static RetVo success(Object data) {
        return new RetVo(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMsg(), data);
    }

    public static RetVo success(HttpStatus status) {
        return new RetVo(status.getCode(), status.getMsg());
    }

    public static RetVo success(HttpStatus status, Object data) {
        return new RetVo(status.getCode(), status.getMsg(), data);
    }

    public static RetVo failed(){
        return new RetVo(HttpStatus.FAILED.getCode(), HttpStatus.FAILED.getMsg());
    }

    public static RetVo failed(String msg){
        return new RetVo(HttpStatus.FAILED.getCode(), msg);
    }

    public static RetVo failed(HttpStatus status) {
        return new RetVo(status.getCode(), status.getMsg());
    }

}

