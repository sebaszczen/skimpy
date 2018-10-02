package pl.sebaszczen.security.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sebaszczen.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<LoginExist, String> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(LoginExist loginExist) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return !loginExist(login);
    }

    private boolean loginExist(String login) {
        boolean loginExist=true;
        if (userService.findByLogin(login)==null){
            loginExist=false;
        }
        return loginExist;
    }
}