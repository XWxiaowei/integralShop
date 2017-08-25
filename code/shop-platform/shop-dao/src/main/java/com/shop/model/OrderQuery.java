package com.shop.model;

/**
 * @Type OrderQuery.java
 * @Desc 订单查询类
 * @author xiang.wei
 * @date 2016年8月3日 下午7:08:57
 * @version
 */
public class OrderQuery {

	private Long orderId;
	private Long merchantId;
	private String userPhone;
	private Long catalogId;
	private Integer orderStatus;
	private String productName;
	private String startTime;
	private String endTime;
	private Integer payType;
	private String transport;
	private Integer transCompany;

	private String secondCatalogId; // 二级分类

	// 以下为分页查询的订单表索引以及步长
	private Integer ipageIndex;
	private Integer ipageSize;
	// 以下为分页查询的订单历史表索引
	private Integer ipageIndexHistory;

	public Integer getIpageIndex() {
		return ipageIndex;
	}

	public void setIpageIndex(Integer ipageIndex) {
		this.ipageIndex = ipageIndex;
	}

	public Integer getIpageSize() {
		return ipageSize;
	}

	public void setIpageSize(Integer ipageSize) {
		this.ipageSize = ipageSize;
	}

	public Integer getIpageIndexHistory() {
		return ipageIndexHistory;
	}

	public void setIpageIndexHistory(Integer ipageIndexHistory) {
		this.ipageIndexHistory = ipageIndexHistory;
	}

	public String getSecondCatalogId() {
		return secondCatalogId;
	}

	public void setSecondCatalogId(String secondCatalogId) {
		this.secondCatalogId = secondCatalogId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public Integer getTransCompany() {
		return transCompany;
	}

	public void setTransCompany(Integer transCompany) {
		this.transCompany = transCompany;
	}

}
