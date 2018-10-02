package pl.sebaszczen.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save (UserDto userDto) throws EmailExistsException;

    List<User> findAll();

    void deleteUser(Long id);

    User findByEmail(String email);

    void updatePassword(String password, Long userId);

    void updateIsActive(boolean isActive,Long userId);

    User findByLogin(String login);
}
