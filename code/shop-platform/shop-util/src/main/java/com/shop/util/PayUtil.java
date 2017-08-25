package com.shop.util;

import java.util.Random;

/**
 * 流水号的生成
 * 
 * @author xiang.wei
 *
 */
public class PayUtil {

	private static String MACHIN_CODE = "9";// 机器码改为1位
	public static final int DEFAULT_EXPIRETIME = 24 * 60;//单位：分钟 ；订单默认有效期 24小时
	

	/**
	 * 生成唯一订单号：18位 ==>13位时间(精确到秒) + 2位随机数 + 3位机器码
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月13日 下午2:32:15
	 * 说明：System.currentTimeMillis()为13位
	 */
	public static Long createOrderId() {
		String uid = UIDUtil.next();
		StringBuffer orderSB = new StringBuffer();
		orderSB.append(uid);
		orderSB.append(MACHIN_CODE);
		return Long.valueOf(orderSB.toString());

	}

	/**
	 *  生成唯一用户id：17位 ==>13位时间(精确到秒) + 3位随机数 + 1位机器码
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月13日 下午2:38:44
	 */
	public static Long createUserId() {
		String uid=UIDUtil.nextSixteen();
		StringBuffer userSB=new StringBuffer();
		userSB.append(uid);
		userSB.append(MACHIN_CODE);
		return Long.valueOf(userSB.toString());

	}
	/**
	 * 生成16位流水号
	 * “%03d”表示的意思是：输出一个数值变量,不足3位在前面补0
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月13日 下午2:49:38
	 */
	public static String createTransaction(){
		return System.currentTimeMillis()+String.format("%03d", new Random().nextInt(999));
	}
	public static String createTransactionShort(){
		Long time=System.currentTimeMillis();
		return String.valueOf(time).substring(2, 13)+String.format("%03d", new Random().nextInt(999));
	}
}
