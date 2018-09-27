package pl.sebaszczen.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
