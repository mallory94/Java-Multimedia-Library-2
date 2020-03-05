package services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceEmail implements Runnable{
	private String email;
	private String resultatAnalyse;
	
	public ServiceEmail(String email, String resultatAnalyse) {
		this.email = email;
		this.resultatAnalyse = resultatAnalyse;
	}
	
	@Override
	public void run() {
		
		try {
			envoyerMail();
			System.out.println("mail envoyé");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void envoyerMail() {
		final String username = "BibliothequeServeur2020@gmail.com";
	    final String password = "Bretteur2020";
	    Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
	    prop.put("mail.smtp.port", "587");
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.starttls.enable", "true"); //TLS
	    
	    Session session = Session.getInstance(prop,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });
	
	    try {
	
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("BibliothequeServeur2020@gmail.com"));
	        message.setRecipients(
	                Message.RecipientType.TO,
	                InternetAddress.parse(email + ", BibliothequeServeur2020@gmail.com")
	        );
	        message.setSubject("Analyse de votre fichier xml");
	        message.setText("Bonjour, \nVous venez tout récemment de nous demander d'analyser votre fichier xml en rapport avec"
	        		+ " une liste de livre. \n" + resultatAnalyse);
	
	        Transport.send(message);
	        
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}
	
}
