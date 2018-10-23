package pl.sebaszczen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sebaszczen.domain.UserDto;

@Controller
public class MenuController {

    @ModelAttribute("user")
    public UserDto UserDto() {
        return new UserDto.Builder().build();
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/chart")
    public String getChart() {
        return "chart";
    }

    @GetMapping("/registration")
    public String saveUser(Model model) {
        model.addAttribute("user", new UserDto.Builder().build());
        return "save";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }
    @GetMapping("/logged")
    public String getLogin(Model model) {
        return "index";
    }


}
