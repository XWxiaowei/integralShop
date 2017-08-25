package com.shop.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除状态
 */
public enum DeleteFlag {

    NO(0, "未删除"),
    YES(1, "已删除"), ;

    private Integer code;

    private String message;

    DeleteFlag(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * @return
     * 功能说明：将枚举值放入为map中
     */
    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (DeleteFlag item : DeleteFlag.values()) {
            map.put(item.getCode(), item.getMessage());
        }
        return map;
    }
}
