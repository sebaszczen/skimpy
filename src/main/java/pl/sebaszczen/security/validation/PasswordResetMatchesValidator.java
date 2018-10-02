package pl.sebaszczen.security.validation;

import pl.sebaszczen.domain.resetPassword.PasswordResetDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordResetMatchesValidator implements ConstraintValidator<PasswordResetMatches, Object> {

    @Override
    public void initialize(PasswordResetMatches passwordMatches) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        PasswordResetDto passwordResetDto = (PasswordResetDto) o;


        return passwordResetDto.getPassword().equals(passwordResetDto.getConfirmPassword());
    }
}