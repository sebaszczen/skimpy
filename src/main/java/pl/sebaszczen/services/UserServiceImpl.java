package pl.sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepo.findByUsername(s);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + s));

        return userOptional.get();
    }

    @Override
    public User save(UserDto userDto) throws EmailExistsException {

        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  userDto.getEmail());
        }
        else {
            User user = new User(userDto.getUsername(), userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
            userRepo.save(user);
            return user;
        }
    }

    private boolean emailExist(String email) {
        User user = findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {

        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.delete(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepo.updatePassword(password, userId);
    }
}