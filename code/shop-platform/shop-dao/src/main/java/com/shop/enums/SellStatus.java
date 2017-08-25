package com.shop.enums;

/**
 *
 */
public enum SellStatus {

    ONSELL(0, "上架"),
    UNSELL(1, "下架");

    private int code;

    private String message;

    SellStatus(int code, String message) {
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
