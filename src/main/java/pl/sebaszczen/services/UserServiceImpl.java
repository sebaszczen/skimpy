package pl.sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.domain.user.UserDto;
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

        Optional<User> userOptional = Optional.ofNullable(findByLogin(s));

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
            User user = new User.Builder().setUsername(userDto.getUserName()).setLogin(userDto.getLogin())
                    .setPassword(passwordEncoder.encode(userDto.getPassword())).setEmail(userDto.getEmail())
                    .setSex(( userDto.getSex())).build();
            userRepo.save(user);
            return user;
        }
    }

    @Override
    public void updadeUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findOne(id);
    }

    private boolean loginExisst(String login) {
        User user = findByLogin(login);
        return user != null;
    }

    private boolean emailExist(String email) {
        User user = findByEmail(email);
        return user != null;
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

    @Override
    public void updateIsActive(boolean isActive, Long userId) {
        User user = userRepo.findOne(userId);
        user.setActive(isActive);
        userRepo.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login);
    }
}