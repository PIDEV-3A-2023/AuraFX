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
}
