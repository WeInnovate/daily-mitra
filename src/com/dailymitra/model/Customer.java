package com.dailymitra.model;

public class Customer {

	private String userName;

	private String name;

	private String email;

	private String mobileNumber;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Customer [userName=" + userName + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + "]";
	}

	public Customer() {
		super();
	}

	public Customer(String userName, String name, String email, String mobileNumber) {
		super();
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
}
