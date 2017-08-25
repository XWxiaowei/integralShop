package com.shop.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明：第三方支付方式
 * 
 * @author wangguangyao@chinamobile.com
 * @date 2015年3月12日下午3:18:11
 * @version V0.1
 * @since JDK1.6
 */
public enum PayType {

	NONE(0, "none", "未选择支付方式"),
	ALIPAY(1, "alipay", "支付宝"), 
	WECHATPAY(2,"wechatpay", "微信支付");

	private Integer code;

	private String message;

	private String name;

	PayType(Integer code, String message, String name) {
		this.code = code;
		this.message = message;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return 功能说明:
	 */
	public static Map<Integer, String> toMap() {
		Map<Integer, String> map = new HashMap<>();
		for (PayType item : PayType.values()) {
			map.put(item.getCode(), item.getMessage());
		}
		return map;
	}

	/**
	 * 
	 * @return 功能说明:
	 */
	public static Map<Integer, String> toNameMap() {
		Map<Integer, String> map = new HashMap<>();
		for (PayType item : PayType.values()) {
			map.put(item.getCode(), item.getName());
		}
		return map;
	}

	/**
	 * 
	 * @return 功能说明:
	 */
	public static Map<String, String> toMsgMap() {
		Map<String, String> map = new HashMap<>();
		for (PayType item : PayType.values()) {
			map.put(item.getMessage(), item.getName());
		}
		return map;
	}
}
