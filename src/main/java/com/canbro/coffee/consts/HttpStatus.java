package com.canbro.coffee.consts;

import lombok.Getter;

/**
 * http状态常量
 */
@Getter
public enum HttpStatus {

    SUCCESS(200, "Success."),
    FAILED(500, "Server error.");

    private final int code;
    private final String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
