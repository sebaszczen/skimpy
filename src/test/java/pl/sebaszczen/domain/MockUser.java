package pl.sebaszczen.domain;

import java.util.ArrayList;
import java.util.List;

public class MockUser {

    public List<User> userList() {
        User user = new User.Builder().setId(1L).setActive(true)
                .setEmail("sebastianszczebiot@gmail.com").setLogin("l").setPassword("p")
                .setUsername("u").build();

        List<User>userList=new ArrayList<>();
        userList.add(user);
        return userList;
    }
}
