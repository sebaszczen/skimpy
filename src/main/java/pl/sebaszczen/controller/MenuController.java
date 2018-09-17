package pl.sebaszczen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/register")
    public String saveUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "save";
    }

    @GetMapping("/logged")
    public String getLogin(Model model) {
        return "logged";
    }
}
