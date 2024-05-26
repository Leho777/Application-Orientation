package com.cytech.model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EnvoieEmail {
	public static void sendEmail(String toAddress, String subject, String message) throws MessagingException {
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "projet.orientation0605@gmail.com";
        String password = "yrpr lzae lsew tjlz"; // Utilisez le mot de passe d'application généré ici

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailFrom));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());
        msg.setText(message);

        Transport.send(msg);
    }
}