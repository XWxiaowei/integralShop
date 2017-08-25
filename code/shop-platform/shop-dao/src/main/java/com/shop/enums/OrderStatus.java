package com.shop.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 功能说明：支付订单状态枚举
 *
 * @author 林峰
 * @version V0.1
 * @date 2016-4-27
 */
public enum OrderStatus {
    SUCCESS(0, "已付款"),//第三方支付成功，积分支付成功，免密支付成功
    FAIL(1, "付款失败"), //下拉框不显示支付失败情况
    WAIT(2, "未付款"),
    CANCEL(3, "已取消"),
    DELIVERED(4, "已发货"),
    REFUNDING(5, "退款中"),
    REFUND(6, "已退款"),
    USED(7, "已使用"),
    REFUND_SECRET(53, "免密支付退款成功"),
    REFUND_REDBACKAGE(52, "电子券退款成功"),
	WAIT_JIFEN(21,"积分已扣减"),
	WAIT_REDPACKET(22,"电子券已发放"),
	WAIT_REDPACKETQUERY(23,"电子券已领用");

    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
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
     */
    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (OrderStatus item : OrderStatus.values()) {
            map.put(item.getCode(), item.getMessage());
        }
        return map;
    }

}
