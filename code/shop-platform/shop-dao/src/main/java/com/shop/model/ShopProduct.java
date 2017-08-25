package com.shop.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShopProduct {

	private Long id;

	private Long productId;

	private String productName;

	private Long merchantId;

	private Long catalogId;

	private BigDecimal price;

	private Integer inventory;

	private Integer selledNum;

	private String titlePicture;

	private String logoPicture;

	private Integer onsell;

	private Date onsellStartTime;

	private Date onsellEndTime;

	private Date createTime;

	private Date updateTime;

	private Integer deleteFlag;

	private String info;
	private BigDecimal marketPrice;
	private BigDecimal costPrice;
	private Integer numLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSelledNum() {
		return selledNum;
	}

	public void setSelledNum(Integer selledNum) {
		this.selledNum = selledNum;
	}

	public Integer getOnsell() {
		return onsell;
	}

	public void setOnsell(Integer onsell) {
		this.onsell = onsell;
	}

	public Date getOnsellStartTime() {
		return onsellStartTime;
	}

	public void setOnsellStartTime(Date onsellStartTime) {
		this.onsellStartTime = onsellStartTime;
	}

	public Date getOnsellEndTime() {
		return onsellEndTime;
	}

	public void setOnsellEndTime(Date onsellEndTime) {
		this.onsellEndTime = onsellEndTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info == null ? null : info.trim();
	}

	public String getTitlePicture() {
		return titlePicture;
	}

	public void setTitlePicture(String titlePicture) {
		this.titlePicture = titlePicture;
	}

	public String getLogoPicture() {
		return logoPicture;
	}

	public void setLogoPicture(String logoPicture) {
		this.logoPicture = logoPicture;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getNumLimit() {
		return numLimit;
	}

	public void setNumLimit(Integer numLimit) {
		this.numLimit = numLimit;
	}

}