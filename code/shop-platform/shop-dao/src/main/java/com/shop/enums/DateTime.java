package com.shop.enums;

public enum DateTime {
	START(0, " 00:00:00"), 
	END(1, " 23:59:59");

	private int code;

	private String message;

	DateTime(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
