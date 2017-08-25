package com.shop.model;

public class ProductQuery {
	private Long merchantId;
	private String productName;
	private Long catalogId;
	private String createTimeStart;
	private String createTimeEnd;
	
	private Integer ipageIndex;
	private Integer ipageSize;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

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
	

}
