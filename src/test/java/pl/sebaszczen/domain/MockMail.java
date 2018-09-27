package pl.sebaszczen.domain;

import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.resetPassword.Mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MockMail {

    public List<Mail> mailList(){
        Mail mail1=new Mail();
        mail1.setFrom("no-reply@memorynotfound.com");
        mail1.setTo("sebastianszczebiot@gmail.com");
        mail1.setSubject("Activate your account and anjoy skimpy :)");

        List<Mail> mailList = new ArrayList<>();
        mailList.add(mail1);
        return mailList;
    }

}
