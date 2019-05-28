package nl.hu.sie.bep.friendspammer;

import javax.mail.MessagingException;

public class EmailException extends MessagingException{

	public EmailException(String message) {
		System.out.println(message);
	}

}
