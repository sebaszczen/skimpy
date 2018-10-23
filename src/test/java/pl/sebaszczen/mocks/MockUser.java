package pl.sebaszczen.mocks;

import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;

@Service
public class MockUser {

    public static User getProperUser() {
        UserDto properUserDto = MockUserDto.getProperUserDto();
        User user = new User.Builder().setEmail(properUserDto.getEmail()).setLastName(properUserDto.getLastName())
                .setLogin(properUserDto.getLogin()).setPassword(properUserDto.getPassword())
                .setUsername(properUserDto.getUserName()).build();
        return user;
    }
}
