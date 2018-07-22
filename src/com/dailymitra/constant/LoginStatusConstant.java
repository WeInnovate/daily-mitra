package com.dailymitra.constant;

public enum LoginStatusConstant {

	EXISTING("Existing"), ADMIN("ADMIN"), NOT_VERIFIED("Not Verified"), VERIFIED("Verified"), BLOCKED("Blocked"),
	NON_EXISTING("Non Existing");

	private String value;

	private LoginStatusConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
