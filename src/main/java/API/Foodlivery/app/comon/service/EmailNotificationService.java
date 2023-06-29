package API.Foodlivery.app.comon.service;

import API.Foodlivery.app.users.entities.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailNotificationService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;

    public void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailOfCreationNewUser(UserDTO user) {

        //System.out.println("SimpleEmail Start");

        String smtpHostServer = "smtp.gmail.com";
        String emailID = fromEmail;


        Properties props = System.getProperties();

        props.put("mail.smtp.port", mailPort);
        props.put("mail.smtp.host", smtpHostServer);
        Session session = Session.getInstance(props, null);

        sendEmail(session, emailID,"Welcome to Foodlivery", "Welcome " + user.getName() + "to the world of Foodlivery");
    }



}
