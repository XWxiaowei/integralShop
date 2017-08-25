package com.shop.dao;

import java.util.List;

import com.shop.model.OrderQuery;
import com.shop.model.ShopOrder;
import com.shop.model.ShopOrder2;

public interface ShopOrderMapper {

	int insert(ShopOrder record);

	ShopOrder2 selectByOrderId(Long orderId);

	int updateByOrderId(ShopOrder record);

	List<ShopOrder> selectOrderByUserId(Long userId);

	int queryPaginationOrderCount(OrderQuery query);

	List<ShopOrder2> queryPaginationOrderPage(OrderQuery query);

}