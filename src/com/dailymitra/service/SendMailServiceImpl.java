package com.dailymitra.service;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailServiceImpl implements SendMailService {
	
	private static ResourceBundle mailResource;

	private static String fromAddrr;

	private static String userName;

	private static String password;



	static {
		mailResource = ResourceBundle.getBundle("com//dailymitra//service//mail", Locale.US);
		fromAddrr = mailResource.getString("from");
		userName = mailResource.getString("username");
		password = mailResource.getString("password");
	}
	
	
	
	

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

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromAddrr));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddrr));

			if (ccAddrr != null) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddrr));
			}

			if (bccAddrr != null) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccAddrr));
			}

			message.setSubject(subject);

			message.setText(body);

			Transport.send(message);

//			TODO: delete this standard output print
			System.out.println("Sent!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);

		}
	}

}
