package pl.sebaszczen.domain;

import pl.sebaszczen.domain.builder.UserBuilder;
import pl.sebaszczen.domain.builder.UserDtoBuilder;

import java.util.ArrayList;
import java.util.List;

public class MockUserDto {

    public List<UserDto> userDtoList() {
        UserDto userDto=new UserDtoBuilder().setEmail("sebatianszczebiot@gmail.com").setLastName("l")
                .setLogin("login").setMatchingPassword("m").setPassword("p").setTerms(true).setUsername("u").getUser();

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        return userDtoList;
    }
}
