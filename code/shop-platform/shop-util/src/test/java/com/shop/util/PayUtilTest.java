package com.shop.util;

import org.junit.Assert;
import org.junit.Test;


/** 
 * @author lixianghz@chinamobile.com   
 * @date 2016年8月29日 下午5:11:28 
 */
public class PayUtilTest {

	
	@Test
	public void testCreateOrder(){
		Assert.assertNotNull(PayUtil.createOrderId());
		Assert.assertEquals(18, String.valueOf(PayUtil.createOrderId()).length());
	}
	
	@Test
	public void testCreateTranstionid(){
		Assert.assertNotNull(PayUtil.createTransaction());
		Assert.assertEquals(16, PayUtil.createTransaction().length());
	}
	@Test
	public void testCreateTranstionShort(){
		Assert.assertNotNull(PayUtil.createTransactionShort());
		Assert.assertEquals(14, PayUtil.createTransactionShort().length());
	}
	@Test
	public void testcreateUserId(){
		Assert.assertNotNull(PayUtil.createUserId());
		Assert.assertEquals(17, String.valueOf(PayUtil.createUserId()).length());
	}
}
