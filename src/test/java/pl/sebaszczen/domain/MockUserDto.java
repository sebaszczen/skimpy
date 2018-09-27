package pl.sebaszczen.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockUserDto {

    public List<UserDto> userDtoList() {
        UserDto userDto=new UserDto.Builder().setEmail("sebatianszczebiot@gmail.com").setLastName("l")
                .setLogin("login").setMatchingPassword("m").setPassword("p").setTerms(true).setUsername("u").build();

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        return userDtoList;
    }
}
