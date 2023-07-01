package API.Foodlivery.app.email;

import API.Foodlivery.app.entities.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailNotificationService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;

    @Autowired
    UtilEmail utilEmail;

    Logger LOGGER = LoggerFactory.getLogger(EmailNotificationService.class);

    public boolean sendEmailOfCreationNewUser(UserDTO user) {
        try{
            LOGGER.info("Sending email...");
            String smtpHostServer = mailHost;
            String emailID = fromEmail;

            Properties props = System.getProperties();

            props.put("mail.smtp.port", mailPort);
            props.put("mail.smtp.host", smtpHostServer);

            Session session = Session.getInstance(props, null);

            utilEmail.sendEmail(session, emailID,
                    "Welcome to Foodlivery",
                    "Welcome " + user.getName() + "to the world of Foodlivery");

            return true;
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return false;
        }
    }



}
