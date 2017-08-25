package com.shop.dao;

import java.util.List;

import com.shop.model.ShopCatalog;

public interface ShopCatalogMapper {

	int insert(ShopCatalog record);

	ShopCatalog selectByCatalogId(Long catalogId);

	int updateByCatalogId(ShopCatalog record);

	List<ShopCatalog> getByParentId(Long parentCatalogid);

	List<ShopCatalog> selectBylevel(Long level);

}