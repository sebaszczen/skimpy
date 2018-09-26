package pl.sebaszczen.domain.builder;


import pl.sebaszczen.domain.UserDto;

public class UserDtoBuilder {
    private String username;
    private String lastName;
    private String login;
    private String password;
    private String matchingPassword;
    private String email;
    private Boolean terms;

    public UserDtoBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDtoBuilder setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        return this;
    }

    public UserDtoBuilder setTerms(Boolean terms) {
        this.terms = terms;
        return this;
    }

    public UserDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserDtoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDto getUser(){
        return new UserDto (username,lastName,login,password,matchingPassword,email,terms);
    }
}
