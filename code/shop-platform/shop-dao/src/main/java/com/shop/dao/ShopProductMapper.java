package com.shop.dao;

import java.util.List;
import java.util.Map;

import com.shop.model.ProductQuery;
import com.shop.model.ShopProduct;

public interface ShopProductMapper {

	int deleteByPrimaryKey(Long id);

	int insert(ShopProduct record);

	int updateByProductId(ShopProduct record);

	ShopProduct selectByProductId(Long productId);
	
	int queryPaginationProductCount(ProductQuery productQuery);
	
	List<ShopProduct> queryPaginationProductPage(ProductQuery productQuery);
	
	List<ShopProduct> queryPromoteProductList(Map<String, Object> param);
}	