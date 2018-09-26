package pl.sebaszczen.controller.activateAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.activateAccount.AccountActivateToken;
import pl.sebaszczen.domain.resetPassword.PasswordResetDto;
import pl.sebaszczen.domain.resetPassword.PasswordResetToken;
import pl.sebaszczen.repository.AccountActivateTokenRepository;
import pl.sebaszczen.repository.PasswordResetTokenRepository;
import pl.sebaszczen.services.UserService;

import javax.validation.Valid;

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