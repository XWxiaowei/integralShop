package com.shop.site.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.shop.model.ShopProduct;
import com.shop.service.ShopProductService;
import com.shop.util.DateTimeUtil;

public class ProductControllerTest extends BaseControllerTest {
	@Autowired
	private ShopProductService productService;

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
		product.setOnsellStartTime(DateTimeUtil.getToday());
		product.setDeleteFlag(0);
		productService.insert(product);
	}

	@Test
	public void mainTest() throws Exception {
		MvcResult mvcResult = mvc.perform(get("/product/main"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertEquals("shop/main", mvcResult.getModelAndView().getViewName());

	}

	@Test
	public void moreTest() throws Exception {
		//不指定产品名称
		MvcResult mvcResult = mvc.perform(get("/product/more").param("iPageIndex", "0").param("iPageSize", "10"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertEquals("shop/more", mvcResult.getModelAndView().getViewName());
		//按产品名称搜索
	    mvcResult = mvc.perform(get("/product/more").param("iPageIndex", "0").param("iPageSize", "10").param("productName", "猫眼"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertEquals("shop/more", mvcResult.getModelAndView().getViewName());

	}

	@Test
	public void detailTest() throws Exception {
		MvcResult mvcResult = mvc
				.perform(
						get("/product/detail").param("productId", "1010101010"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertEquals("shop/detail", mvcResult.getModelAndView().getViewName());

	}

}
