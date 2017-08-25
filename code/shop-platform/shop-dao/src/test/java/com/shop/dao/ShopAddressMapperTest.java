package com.shop.dao;



import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.model.ShopAddress;

public class ShopAddressMapperTest extends AbstractTestCase {
	@Autowired
	private ShopAddressMapper addressMapper;
	ShopAddress address = new ShopAddress();

	@Before
	public void setUp() {
		Date date = new Date();
		address.setId(111L);
		address.setAddressId(111L);
		address.setUserId(11L);
		address.setReceiverName("张三");
		address.setReceiverPhone("123456789");
		address.setEmail("123456@qq.com");
		address.setCreateTime(date);
		address.setUpdateTime(date);
		address.setDeleteFlag(0);
	}

	@Test
	public void insertTest() {
		int result = addressMapper.insert(address);
		assertEquals(1, result);

	}
	
	@Test
	public void selectByPrimaryKeyTest(){
		addressMapper.insert(address);
		ShopAddress address1=addressMapper.selectByAddressId(111L);
		Assert.assertNotNull(address1);
	}
}
