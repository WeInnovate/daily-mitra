package com.dailymitra.service;

public class SendMailServiceImpl implements SendMailService {

	@Override
	public void sendMail(String toAddrr, String subject, String body) {
		sendMail(toAddrr, null, null, subject, body);
	}

	@Override
	public void sendMail(String toAddrr, String ccAddrr, String subject, String body) {
		sendMail(toAddrr, ccAddrr, null, subject, body);
	}

	@Override
	public void sendMail(String toAddrr, String ccAddrr, String bccAddrr, String subject, String body) {

	}

}
