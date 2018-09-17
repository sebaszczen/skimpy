package pl.sebaszczen.security;

import org.passay.*;
import org.passay.dictionary.WordListDictionary;
import org.passay.dictionary.WordLists;
import org.passay.dictionary.sort.ArraysSort;
import pl.sebaszczen.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private DictionaryRule dictionaryRule;

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                // at least 8 characters
                new LengthRule(8, 30),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()

                // no common passwords
//                dictionaryRule
        ));
        User user = (User) o;


        RuleResult result = validator.validate(new PasswordData(user.getPassword()));



        List<String> messages = validator.getMessages(result);

        if (!user.getPassword().equals(user.getMatchingPassword())) {
            messages.add("password don't match");
        }

        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return result.isValid() && user.getPassword().equals(user.getMatchingPassword());

//        return user.getPassword().equals(user.getMatchingPassword());
    }
}