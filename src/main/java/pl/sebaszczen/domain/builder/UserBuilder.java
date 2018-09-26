package pl.sebaszczen.domain.builder;


import pl.sebaszczen.domain.User;

public class UserBuilder {
    private Long id;
    private String username;
    private String login;
    private String password;
    private String email;
    private boolean active = false;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public User getUser(){
        return new User(id,username,login,password,email,active);
    }
}
