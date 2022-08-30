import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class InClass {
	public static void sendPlainTextEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
// *** BEGIN CHANGE
        properties.put("mail.smtp.user", userName);
        //properties.put("mail.smtp.timeout", "2000");

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);
// *** END CHANGE

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setText(message);

// *** BEGIN CHANGE
        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect(userName, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
// *** END CHANGE

    }
	
	public static final void prepareAndSendEmail(String host, int port,
            final String userName, final String password, String toAddress,
            String subject, String message) {

        //Spring Framework JavaMailSenderImplementation    
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        //setting username and password
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        //setting Spring JavaMailSenderImpl Properties
        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.starttls.enable", "true");
        mailProp.put("mail.smtp.starttls.required", "true");
        mailProp.put("mail.debug", "true");
        mailProp.put("mail.smtp.ssl.enable", "true");
        mailProp.put("mail.smtp.user", userName);

        //preparing Multimedia Message and sending
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(userName);
            helper.setSubject("I achieved the Email with Java 7 and Spring");
            helper.setText(message, true);//setting the html page and passing argument true for 'text/html'

            
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void main (String args[]) throws IOException, InterruptedException {
    	String from = "czvetkova.alisa@yandex.ru";//"petrova11072002@mail.ru";         // sender email
        String to = "petrova11072002@mail.ru";       // receiver email
        String host = "smtp.yandex.ru";
        String password =  "alissa15451F";//"S6QbTnXgUX7uE1UpCaDS";
        String port = "465";
        
        from = "petrova11072002@mail.ru";//"petrova11072002@mail.ru";         // sender email
        to = "petrova11072002@mail.ru";       // receiver email
        host = "smtp.mail.ru";
        password =  "S6QbTnXgUX7uE1UpCaDS";//"S6QbTnXgUX7uE1UpCaDS";"alissa15451F"
        port = "465";

        // outgoing message information
        String subject = "Hello my friend";
        String message = "Hi guy, Hope you are doing well. Duke.";


        try {
        	prepareAndSendEmail(host, 465, from, password, to, subject, message);
           // sendPlainTextEmail(host, port, from, password, to, subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}