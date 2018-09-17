package pl.sebaszczen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.User;
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
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public List<User> findAll() {

        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.delete(id);
    }
}