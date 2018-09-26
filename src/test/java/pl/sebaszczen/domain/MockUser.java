package pl.sebaszczen.domain;

import pl.sebaszczen.domain.builder.UserBuilder;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

    public List<User> userList() {
        User user = new UserBuilder().setId(1L).setActive(true)
                .setEmail("sebastianszczebiot@gmail.com").setLogin("l").setPassword("p")
                .setUsername("u").getUser();

        List<User>userList=new ArrayList<>();
        userList.add(user);
        return userList;
    }
}
