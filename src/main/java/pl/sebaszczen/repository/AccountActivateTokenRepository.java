package pl.sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sebaszczen.domain.activateAccount.AccountActivateToken;
import pl.sebaszczen.domain.resetPassword.PasswordResetToken;

@Repository
public interface AccountActivateTokenRepository extends JpaRepository<AccountActivateToken, Long> {

    AccountActivateToken findByToken(String token);

}