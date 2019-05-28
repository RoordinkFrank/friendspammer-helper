package nl.hu.sie.bep.friendspammer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class EmailSender {
	//private static Logger logger = LoggerFactory.getLogger(EmailSender.class);
	
	
	private EmailSender() {}
	
	public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) throws EmailException {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");

		String username = "7a1eaba28156ea";
		String password = "45e7c5f4925a9d";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
			@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spammer@spammer.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			
			if (asHtml) {
					message.setContent(messageBody, "text/html; charset=utf-8");
			} else {
				message.setText(messageBody);	
			}
			Transport.send(message);

			MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);
			
			

		} catch (MessagingException e) {
			throw new EmailException(e.getMessage());
		}
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) throws EmailException {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.auth", "true");
		
		String username = "YOUR MAIL USERNAME";
		String password = "YOUR MAIL PASSWORD";

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
			@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {

			for (int index = 0; index < toList.length; index++) {
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("spammer@spammer.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toList[index]));
				message.setSubject(subject);
				
				if (asHtml) {
						message.setContent(messageBody, "text/html; charset=utf-8");
				} else {
					message.setText(messageBody);	
				}
				Transport.send(message);
				System.out.println("Done");
				//logger.info("Done");
			}

		} catch (MessagingException e) {
			throw new EmailException(e.getMessage());
		}
	}
	
}
