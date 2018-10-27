package pl.sebaszczen.controller.thymeleafConroller.hanger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sebaszczen.domain.user.User;

import java.security.Principal;

@Controller
public class hanger {

    @GetMapping("play")
    public String play(Principal principal, Model model) {
        User user= (User)(((Authentication)principal).getPrincipal());
        model.addAttribute("user", user);
        return "game/hanger";
    }
}
