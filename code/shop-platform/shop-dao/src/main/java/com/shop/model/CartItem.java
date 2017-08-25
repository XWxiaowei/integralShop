package com.shop.model;

import java.math.BigDecimal;

/**
 * 功能说明：购物项封装
 * 
 * @author xiang.wei
 */
public class CartItem {
	private ShopProduct product; // 购物项中包含的商品对象
	private Integer count; // 购物商品的数量

	public ShopProduct getProduct() {
		return product;
	}

	public void setProduct(ShopProduct product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public CartItem(ShopProduct product, Integer count) {
		super();
		if (product == null) {
			throw new NullPointerException();
		} else {
			this.product = product;
		}
		this.count = count;
	}

	public BigDecimal getSubTotal() {
		BigDecimal price = product.getPrice();
		if (price==null) {
			throw new NullPointerException();
		}
		return price.multiply(new BigDecimal(count));
	}
}
