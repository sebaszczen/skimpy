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

    public UserDto(final Builder builder) {
        this.username=builder.username;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.matchingPassword = builder.matchingPassword;
        this.email = builder.email;
        this.terms = builder.terms;
    }

    public UserDto() {
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

    public static class Builder{
        private String username;
        private String lastName;
        private String login;
        private String password;
        private String matchingPassword;
        private String email;
        private Boolean terms;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setMatchingPassword(String matchingPassword) {
            this.matchingPassword = matchingPassword;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setTerms(Boolean terms) {
            this.terms = terms;
            return this;
        }
        public UserDto build(){
            return new UserDto(this);
        }
    }
}
