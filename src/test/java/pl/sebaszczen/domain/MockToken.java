package pl.sebaszczen.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sebaszczen.domain.activateAccount.AccountActivateToken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MockToken {

    @Autowired
    MockUser mockUser;

    public List<AccountActivateToken> accountActivateTokens(){
        AccountActivateToken accountActivateToken=new AccountActivateToken();
        accountActivateToken.setToken(UUID.randomUUID().toString());
        accountActivateToken.setUser(mockUser.userList().get(0));
        List<AccountActivateToken> accountActivateTokens = new ArrayList<>();
        accountActivateTokens.add(accountActivateToken);

        return accountActivateTokens;
    }
}

