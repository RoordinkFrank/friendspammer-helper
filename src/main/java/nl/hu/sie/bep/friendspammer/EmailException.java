package nl.hu.sie.bep.friendspammer;

import javax.mail.MessagingException;

public class EmailException extends MessagingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3388823713749437043L;

	public EmailException(String errorMessage) {
		super(errorMessage);
	}
}
