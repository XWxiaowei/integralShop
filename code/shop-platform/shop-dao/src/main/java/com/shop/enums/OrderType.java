package com.shop.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 功能说明：支付订单类型枚举
 *
 * @author 林峰
 * @version V0.1
 * @date 2016-4-27
 */
public enum OrderType {
	COMMON(0, "普通订单"), SHOPPINGCART(1, "购物车订单");

	private Integer code;

	private String message;

	OrderType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static Map<Integer, String> toMap() {
		Map<Integer, String> map = new HashMap<>();
		for (OrderType item : OrderType.values()) {
			map.put(item.getCode(), item.getMessage());
		}
		return map;
	}

}
