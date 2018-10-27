package pl.sebaszczen.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.domain.user.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save (UserDto userDto) throws EmailExistsException;

    void updadeUser(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteUser(Long id);

    User findByEmail(String email);

    void updatePassword(String password, Long userId);

    void updateIsActive(boolean isActive,Long userId);

    User findByLogin(String login);
}
