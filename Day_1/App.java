package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static void main(String[] args) {

        System.out.println("Preparing the test of email...");

        String message = "Hello, this is a message we have to test.";   /* hare we creat an object we need */
        String subject = "Test Email";
        String to = "awaletushar403@gmail.com";
        String from = "tusharawale904904@gmail.com";

        sendEmail(message, subject, to, from);
    }

    private static void sendEmail(String message, String subject, String to, String from) {

        String host = "smtp.gmail.com";  //we creat an host object for smtp.gmail.com

        // Setting system properties
        Properties properties = new Properties();
        System.out.println("PROPERTIES: " + properties);

        // Configuration settings for Gmail
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Session with Authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("tusharawale904904@gmail.com", "Password");
            }
        });

        session.setDebug(true); // Enable debug logs

        try {

            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);


            Transport.send(m);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
