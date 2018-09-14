package pl.sebaszczen.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.sebaszczen.domain.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save (User user);

    List<User> findAll();

    void deleteUser(Long id);
}