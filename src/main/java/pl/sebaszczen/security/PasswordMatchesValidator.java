package pl.sebaszczen.security;

import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDto user = (UserDto) o;


        return user.getPassword().equals(user.getMatchingPassword());
    }
}