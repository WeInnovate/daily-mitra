package com.dailymitra.util;

import java.util.Random;

public class IdGenerator {

	private final static String itemPrefix = "ITM";

	public IdGenerator() {
	}

	public static String getItemId() {
		return itemPrefix + "_" + System.currentTimeMillis();
	}

	public static String getOtp() {
		//return ((int) Math.random() * 10000) + "";
		Random rnd = new Random();
		String otp = rnd.nextInt(10000) + ""; 
		System.out.println(otp);
		return otp;
	}

}
