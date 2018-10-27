package pl.sebaszczen.mocks;

import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.user.UserDto;

@Service
public class MockUserDto {

    public static UserDto getProperUserDto() {
        UserDto userDtoProper= new UserDto.Builder().setEmail("email@gmail.com").setLastName("l")
                .setLogin("login").setMatchingPassword("Zaq1@wsx").setPassword("Zaq1@wsx")
                .setTerms(true).setUsername("s").build();
        return userDtoProper;
    }
}
