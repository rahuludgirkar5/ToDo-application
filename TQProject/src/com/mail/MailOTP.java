package com.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailOTP {

	public static String sendMail(String useremail) {
		
		final String adminEmail = "ballikali4@gmail.com";
		final String password = "nevergiveup@1";
		
		Properties prop=new Properties();
		
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance(prop,new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
	
				return new PasswordAuthentication(adminEmail, password);
				
			}
		});
		
		Integer otpno = (int)(Math.random() * 900000) + 100000;
		
		String otp =otpno.toString();
		
		System.out.println(otp);
		
		try {
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(adminEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(useremail));
			msg.setSubject("Verification Mail...");
			msg.setText("Enter OTP in Website Prompt Box \n OTP : "+otp);
			
			Transport.send(msg);
			System.out.println("Mail Sent....");
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return otp;
	}

}
