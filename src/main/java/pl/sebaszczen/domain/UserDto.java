package pl.sebaszczen.domain;

import org.hibernate.validator.constraints.NotEmpty;
import pl.sebaszczen.security.PasswordMatches;
import pl.sebaszczen.security.PasswordStrength;
import pl.sebaszczen.security.ValidEmail;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    @PasswordStrength
    private String password;
    @NotNull
    @NotEmpty
    private String matchingPassword;
    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @AssertTrue
    private Boolean terms;

    public UserDto(String username, String lastName, String login, String password, String matchingPassword, String email, Boolean terms) {
        this.username = username;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.terms = terms;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
