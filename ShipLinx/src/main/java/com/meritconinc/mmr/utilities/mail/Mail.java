/**
 * Workfile   :      Mail 
 * Date       :      12/12/2013
 * Version    :      1.0
 * @author    :      Mitosis - Siva
 **/
package com.meritconinc.mmr.utilities.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;



public class Mail {

	Logger log = Logger.getLogger(Mail.class);

	private Session session;
	
	private Mail() {}

	/**
	 * this method is used to get the instance of the Mail Class
	 * 
	 * @param properties properties for the mail
	 * @return instance of the Mail class.
	 */
	public static Mail getInstace(final Properties properties) {
		Mail mail = new Mail();
		final String username = properties.getProperty("email");
		final String password = properties.getProperty("password");
		if (password == null){
			mail.session = Session.getDefaultInstance(properties, null);
		}else{
			mail.session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
		}
		return mail;
	}

	/**
	 * this method is used to send mail
	 * 
	 * @param from e-mail address of the sender.
	 * @param to e-mail address of the receiver.
	 * @param cc e-mail address of the cc.
	 * @param subject subject of the e-mail.
	 * @param body body of the e-mail.
	 * @param bodyType bodyType of the e-mail.
	 * @return 0 if the mail is sent, otherwise error message.
	 */
	public String send(String from, String[] to, String[] cc, String subject,
			String body, String bodyType) {
		Message message = null;
		try {
			message = new MimeMessage(this.session);
			if (from != null) {
				message.setFrom(new InternetAddress(from));
			}
			if (subject != null) {
				message.setSubject(subject);
			}
			message.setContent(body, bodyType);
			
			if (to != null && to.length > 0) {
				InternetAddress[] toListAddresses = new InternetAddress[to.length];
				for (int i = 0; i < to.length; i++) {
					toListAddresses[i] = new InternetAddress(to[i]);
				}
				message.setRecipients(Message.RecipientType.TO,
						toListAddresses);
			} 
			if (cc != null && cc.length > 0) {
				InternetAddress[] ccListAddresses = new InternetAddress[cc.length];
				for (int i = 0; i < cc.length; i++) {
					ccListAddresses[i] = new InternetAddress(cc[i]);
				}
				message.setRecipients(Message.RecipientType.CC,
						ccListAddresses);
			} 
			Transport.send(message);

		} catch (Exception e) {
			log.fatal("Not able send email", e);
			return e.getMessage();
		}
		return "0";
	}
}
