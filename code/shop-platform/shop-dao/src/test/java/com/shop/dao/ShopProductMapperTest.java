package com.shop.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.model.ShopProduct;

public class ShopProductMapperTest extends AbstractTestCase {
	@Autowired
	private ShopProductMapper shopProductMapper;

	@Before
	public void setUp() {
		ShopProduct product = new ShopProduct();
		product.setId(1010101010L);
		product.setProductId(1010101010L);
		product.setProductName("猫眼电影票");
		product.setMerchantId(10001L);
		product.setCatalogId(10001L);
		product.setPrice(BigDecimal.ONE);
		product.setInventory(100);
		product.setSelledNum(10);
		product.setTitlePicture("201610211121440.jpg");
		product.setLogoPicture("201610211121441.jpg");
		product.setOnsellStartTime(new Date());
		product.setDeleteFlag(0);
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		shopProductMapper.insert(product);
	}

	@Test
	public void selectByPrimaryKeyTest() {
		ShopProduct shopProduct = shopProductMapper.selectByProductId(1010101010L);
		Assert.assertNotNull(shopProduct);
	}
}
