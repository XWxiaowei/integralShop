package com.shop.dao;

import java.util.List;

import com.shop.model.ShopMerchant;

public interface ShopMerchantMapper {

	int insert(ShopMerchant record);

	ShopMerchant selectByMerchantId(Long merchantId);

	int updateByPrimaryKeySelective(ShopMerchant record);

	List<ShopMerchant> selectAll();

}