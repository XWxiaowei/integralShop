package com.shop.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: wangguangyao@chinamobile.com Project: migupay Date: 2015/5/4 0004
 * Time: 下午 5:10
 */
public class UIDUtilTest {

	@Test
	public void testNext() throws Exception {
		Assert.assertEquals(15, UIDUtil.next().length());
		Assert.assertNotEquals(UIDUtil.next(), UIDUtil.next());
	}

	@Test
	public void testNextSixteen() {
		Assert.assertEquals(16, UIDUtil.nextSixteen().length());
		Assert.assertNotEquals(UIDUtil.nextSixteen(), UIDUtil.nextSixteen());
	}
}