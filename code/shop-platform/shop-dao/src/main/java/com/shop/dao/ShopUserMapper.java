package com.shop.dao;

import com.shop.model.ShopUser;

public interface ShopUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);
}