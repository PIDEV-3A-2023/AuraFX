/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Cyrine
 */
public class SendMail {

    private String host, port = "587";

    public SendMail(String mailFrom, String password, String mailTo, String subject, String message) throws Exception {

        this.host = "smtp.gmail.com";
        System.out.println(mailTo);
        sendEmail(host, port, mailFrom, password, mailTo, subject, message);
    }

    private void sendEmail(String host, String port, final String userName, final String password, String toAddress, String subject, String message) throws Exception {
        Properties properties = new Properties();
       // activer le protocole TLS.
        properties.setProperty("mail.smtp.starttls.enable", "true");
        //éfinition d'une propriété pour spécifier le protocole SSL à utiliser.
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        properties.put("mail.debug", "true");
        //Affiche l'adresse e-mail du destinataire à la console.
        System.out.println(toAddress);
        //Création d'un objet Authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
        System.out.println(toAddress);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

    
        msg.setContent(multipart);

        Transport.send(msg);
    }


}
