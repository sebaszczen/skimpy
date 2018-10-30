package pl.sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import pl.sebaszczen.domain.password.Mail;
import sun.security.provider.certpath.SunCertPathBuilderException;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Service
public class EmailService {
    private Logger logger = Logger.getLogger(String.valueOf(EmailService.class));
    private boolean success = true;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public boolean sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process((String) mail.getModel().get("templateHtml"), context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            try {
                emailSender.send(message);
            } catch (MailSendException exception) {
                logger.info(exception.getMessage());
                exception.printStackTrace();
                success = false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }
}