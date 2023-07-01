package API.Foodlivery.app.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
@Service
public class UtilEmail {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;

    Logger LOGGER = LoggerFactory.getLogger(UtilEmail.class);

    public void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //Settaggio header
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            //Inserimento del mittente
            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            //Inserimento del oggetto (titolo)
            msg.setSubject(subject, "UTF-8");
            //Inserimento del testo
            msg.setText(body, "UTF-8");
            //Data di invio
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            LOGGER.info("Message is ready");
            Transport.send(msg);

            LOGGER.info("Email Sent Successfully!!");
        }
        catch (Exception e) {
            LOGGER.warn("Email not sent");
            e.printStackTrace();
        }
    }
}
