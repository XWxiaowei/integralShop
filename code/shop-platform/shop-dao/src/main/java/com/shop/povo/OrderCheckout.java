package com.shop.povo;

import java.io.Serializable;
import java.util.List;

import com.shop.model.ShopAddress;
import com.shop.model.ShopCatalog;
import com.shop.model.ShopMerchant;
import com.shop.model.ShopOrder;
import com.shop.model.ShopProduct;

public class OrderCheckout implements Serializable{
	private static final long serialVersionUID = -4725502295671612815L;
	private ShopOrder order;
	private ShopProduct product;
	private List<ShopAddress> addressList;
	private ShopMerchant merchant;
	private ShopCatalog catalog;
	public ShopOrder getOrder() {
		return order;
	}
	public void setOrder(ShopOrder order) {
		this.order = order;
	}
	public ShopProduct getProduct() {
		return product;
	}
	public void setProduct(ShopProduct product) {
		this.product = product;
	}
	
	public List<ShopAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<ShopAddress> addressList) {
		this.addressList = addressList;
	}
	public ShopMerchant getMerchant() {
		return merchant;
	}
	public void setMerchant(ShopMerchant merchant) {
		this.merchant = merchant;
	}
	public ShopCatalog getCatalog() {
		return catalog;
	}
	public void setCatalog(ShopCatalog catalog) {
		this.catalog = catalog;
	}
	
}
