package com.shop.util;

import java.util.Date;

public class UIDUtil {
	private static Date date = new Date();
	private static int seq = 0;
	private static int seqNew = 10;
	private static final int ROTATION = 999;
	private static final int ROTATION_NEW = 99;

	public static synchronized String next() {
		if (seq > ROTATION) {
			seq = 0;
		}
		date.setTime(System.currentTimeMillis());
		return String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$d", date, seq++);
	}

	public static synchronized String nextSixteen() {
		if (seqNew>ROTATION_NEW) {
			seqNew++;
		}
		date.setTime(System.currentTimeMillis());
		return String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$d", date, seqNew++);
	}
}
