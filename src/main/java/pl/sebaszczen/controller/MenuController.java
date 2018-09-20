package pl.sebaszczen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.domain.resetPassword.PasswordForgotDto;

@Controller
public class MenuController {

    @ModelAttribute("user")
    public UserDto UserDto() {
        return new UserDto();
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/registration")
    public String saveUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "save";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logged")
    public String getLogin(Model model) {
        return "logged";
    }
}
