package com.dailymitra.util;

public class IdGenerator {

	private final static String itemPrefix = "ITM";

	private IdGenerator() {
	}

	public static String getItemId() {
		return itemPrefix + "_" + System.currentTimeMillis();
	}

	public static String getOtp() {
		return ((int) Math.random() * 10000) + "";
	}

}
