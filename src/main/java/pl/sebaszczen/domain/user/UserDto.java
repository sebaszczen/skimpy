package pl.sebaszczen.domain.user;

import org.hibernate.validator.constraints.NotEmpty;
import pl.sebaszczen.security.validation.LoginExist;
import pl.sebaszczen.security.validation.PasswordMatches;
import pl.sebaszczen.security.validation.PasswordStrength;
import pl.sebaszczen.security.validation.ValidEmail;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String userName;
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    @LoginExist
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
    private Sex sex;

    enum Sex{
        WOMAN,MAN,NOTSURE;
    }


    public UserDto(final Builder builder) {
        this.userName =builder.username;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.matchingPassword = builder.matchingPassword;
        this.email = builder.email;
        this.terms = builder.terms;
        this.sex = builder.sex;
    }

    public UserDto() {
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        private Sex sex;

        public void setSex(Sex sex) {
            this.sex = sex;
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userName, userDto.userName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(login, userDto.login) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(matchingPassword, userDto.matchingPassword) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(terms, userDto.terms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, lastName, login, password, matchingPassword, email, terms);
    }
}
