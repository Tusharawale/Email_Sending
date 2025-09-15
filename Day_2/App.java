package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

public class App {
    public static void main(String[] args) {
        System.out.println("Preparing the test of email...");

        String message = "Hello, this is a message we have to test.";
        String subject = "Test Email";
        String to = "awaletushar403@gmail.com";
        String from = "tusharawale904904@gmail.com";

     
        String password = System.getenv("EMAIL_PASSWORD");
        if (password == null || password.isEmpty()) {
            System.err.println("❌ Email password not found. Set 'EMAIL_PASSWORD' environment variable.");
            return;
        }

        // This is an path of an attachment from local drive
        String attachmentPath = "C:/Users/ASUS TUF/Downloads/OPTION-1 (1).pdf"; // Change to your file or "" for no attachment

        
        sendEmail(message, subject, to, from, password, attachmentPath);
    }



    private static void sendEmail(String message, String subject, String to, String from, String password, String attachmentPath) {
        final String smtpHost = "smtp.gmail.com";
        final String smtpPort = "465";

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);

            // Create the message part for the HTML content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");  // use the original message parameter or build your HTML here

            // Create Multipart container
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Add attachment if path is provided
            if (attachmentPath != null && !attachmentPath.isEmpty()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(source.getName());
                multipart.addBodyPart(attachmentPart);
            }

            // Set the complete message parts
            mimeMessage.setContent(multipart);

            Transport.send(mimeMessage);
            System.out.println("✅ Email sent successfully with attachment to " + to);
        } catch (MessagingException e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
