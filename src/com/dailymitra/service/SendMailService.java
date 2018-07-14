package com.dailymitra.service;

public interface SendMailService {

	void sendMail(String toAddrr, String subject, String body);

	void sendMail(String toAddrr, String ccAddrr, String subject, String body);

	void sendMail(String toAddrr, String ccAddrr, String bccAddrr, String subject, String body);
}
