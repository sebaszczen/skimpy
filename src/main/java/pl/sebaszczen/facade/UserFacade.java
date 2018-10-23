package pl.sebaszczen.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.UserService;

import java.util.List;

@Service
public class UserFacade {

    @Autowired
    private UserService userService;

    public User createUserAccount(UserDto accountDto) {
        User registered = null;
        try {
            registered = userService.save(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public User findUserById (Long id) {
        User user = userService.findById(id);
        return user;
    }
}
