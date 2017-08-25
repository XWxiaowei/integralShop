package com.shop.dao;

import java.util.List;

import com.shop.model.ShopAddress;

public interface ShopAddressMapper {

	int insert(ShopAddress record);

	ShopAddress selectByAddressId(Long id);

	int updateByAddressId(ShopAddress record);

	List<ShopAddress> selectByUserId(Long userId);

}