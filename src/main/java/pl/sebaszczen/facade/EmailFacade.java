package pl.sebaszczen.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.domain.token.AccountActivateToken;
import pl.sebaszczen.domain.password.Mail;
import pl.sebaszczen.repository.AccountActivateTokenRepository;
import pl.sebaszczen.services.EmailService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailFacade {

    @Autowired
    private AccountActivateTokenRepository accountActivateTokenRepository;
    @Autowired
    private EmailService emailService;

    public boolean activateToken(User user, HttpServletRequest request) {
        AccountActivateToken token = new AccountActivateToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        accountActivateTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Activate your account and anjoy skimpy :)");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skimpy.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/activate-account?token=" + token.getToken());
        model.put("templateHtml", "email/activate-account-email-template");
        mail.setModel(model);
        boolean success= emailService.sendEmail(mail);

        return success;
    }
}
