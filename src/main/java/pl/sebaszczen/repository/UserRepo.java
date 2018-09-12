package pl.sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sebaszczen.domain.User;

public interface UserRepo extends JpaRepository<User,Long> {
    java.util.Optional<User> findByUsername(String name);
}
