package com.example.helloservice.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public String sendEmail() {
        // Recipient's email
        String to = "priyanath@wired2perform.com";

        // Sender's email
        String from = "spjartz@gmail.com";
        String host = "smtp.gmail.com"; // SMTP server (like smtp.gmail.com for Gmail)

        // Set system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Port for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("spjartz@gmail.com", "qetc qffw surb pysp");
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header
            message.setFrom(new InternetAddress(from));

            // Set To: header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject
            message.setSubject("Sample Email Subject");

            // Set the body of the email
            message.setText("This is a sample email body!");

            // Send the message
            Transport.send(message);
            return "Email sent successfully!";

        } catch (MessagingException e) {
           return e.getMessage();
        }
    }
}
