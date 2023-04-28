/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author azerb
 */
public class EmailSender {
     public static void sendEmail(String recipientEmail, String subject, String message) throws MessagingException {
     // Créer une propriété pour configurer l'envoi de l'email
        Properties properties = new Properties();
       /* props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       props.put("mail.smtp.ssl.trust", "*");*/
       properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Créer une session avec l'adresse e-mail et le mot de passe de l'expéditeur
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("azer.bennasr@esprit.tn", "201JMT1764");
            }
        });

        // Créer un message à envoyer
        Message emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress("azer.bennasr@esprit.tn"));
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        emailMessage.setSubject(subject);
        emailMessage.setText(message);

        // Envoyer le message
        Transport.send(emailMessage);
    }
     public static void with(String recipientEmail, String subject, String messages){
         Properties props;
		Session session;
		MimeMessage message;

		props = new Properties();
		props.put("com.hof.email.starttime","20170519094544");
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.connectiontimeout","60000");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","25");
                props.put("mail.smtp.ssl.trust","*");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.timeout","60000");
                props.put("mail.transport.protocol","smtp");
                props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("azer.bennasr@esprit.tn", "201JMT1764");
			}
		};
		
		session = Session.getInstance(props, auth);

		try {

			InternetAddress[] recipients = new InternetAddress[1];
			recipients[0] = new InternetAddress(recipientEmail);
			//recipients[1] = new InternetAddress("recipient1@hotmail.com");
			//recipients[2] = new InternetAddress("recipient2@domainname.com");
			//recipients[3] = new InternetAddress("recipient3@somedomain.ch");
			//recipients[4] = new InternetAddress("recipient4@gtv.org");
			// ...

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress("azer.bennasr@esprit.tn"));
			message.addRecipients(Message.RecipientType.TO, recipients);
			message.setSubject("The First Test Email");
			//message.setText("This is the first test email. ");
			
			BodyPart attachment1 = new MimeBodyPart();
			attachment1.setDataHandler(new DataHandler(new FileDataSource("facture.pdf")));
			attachment1.setFileName("facture.pdf");
			
			/*BodyPart attachment2 = new MimeBodyPart();
			attachment2.setDataHandler(new DataHandler(new FileDataSource("file.txt")));
			attachment2.setFileName("file.txt");*/
			
			BodyPart emailText = new MimeBodyPart();
			emailText.setText(messages);
			
			Multipart multipartContent = new MimeMultipart();
			multipartContent.addBodyPart(attachment1);
			//multipartContent.addBodyPart(attachment2);
			multipartContent.addBodyPart(emailText);
			
			message.setContent(multipartContent);

			Transport.send(message);
			
			System.out.println("Email sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
     }
}
