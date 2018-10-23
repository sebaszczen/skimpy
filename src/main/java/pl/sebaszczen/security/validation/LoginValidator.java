package pl.sebaszczen.security.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sebaszczen.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class LoginValidator implements ConstraintValidator<LoginExist, String> {

    private final Logger LOGGER = Logger.getLogger(String.valueOf(LoginValidator.class));

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
        try {
            if (userService.findByLogin(login).equals(null)) {
            }
        }
        catch (NullPointerException e){
            LOGGER.info("no user found with login: "+login);
            loginExist = false;
        }
        return loginExist;
    }
}