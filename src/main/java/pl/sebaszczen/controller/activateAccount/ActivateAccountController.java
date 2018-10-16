package pl.sebaszczen.controller.activateAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.activateAccount.AccountActivateToken;
import pl.sebaszczen.repository.AccountActivateTokenRepository;
import pl.sebaszczen.services.UserService;

@Controller
@RequestMapping("/activate-account")
public class ActivateAccountController {
    @Autowired private UserService userService;
    @Autowired private AccountActivateTokenRepository accountActivateTokenRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        AccountActivateToken accountActivateToken = accountActivateTokenRepository.findByToken(token);
        if (accountActivateToken == null) {
            model.addAttribute("error", "Could not find activate account token.");
        }
        else {
            User user = accountActivateToken.getUser();
//            user.setActive(true);
            userService.updateIsActive(true,user.getId());

        }

        return "redirect:/login?activateSuccess";
    }
}