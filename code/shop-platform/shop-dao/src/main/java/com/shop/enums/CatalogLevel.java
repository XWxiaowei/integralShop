package com.shop.enums;

public enum CatalogLevel {
	FIRST(1L, "一级类名"), SECOND(2L, "二级类目");

	private long code;
	private String message;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private CatalogLevel(long code, String message) {
		this.code = code;
		this.message = message;
	}

}
