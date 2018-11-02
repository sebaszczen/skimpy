package pl.sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import pl.sebaszczen.configuration.ConsolerData;
import pl.sebaszczen.domain.password.Mail;

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
                    success = true;
                } catch (MailSendException exception) {
                    logger.info(ConsolerData.ANSI_PURPLE +""+exception.getMessage()+""+ ConsolerData.ANSI_RESET);
                    tryAgainAfterTwoMinutes(message);
                    success = false;
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    private void tryAgainAfterTwoMinutes(MimeMessage message) {
        Runnable tryAgainAfterTwoMinutes=()->{
            do {
                try {
                    try {
                        logger.info("next try, watek spi");
                        Thread.sleep(120000);
                        logger.info("pobudka");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     emailSender.send(message);
                    success = true;
                } catch (MailException exception) {
                    logger.info(ConsolerData.ANSI_PURPLE +""+exception.getMessage()+""+ ConsolerData.ANSI_RESET);
                    success = false;
                }
            }
            while (!success);
        };
        Thread t2 = new Thread(tryAgainAfterTwoMinutes);
        t2.start();
    }
}