package pl.sebaszczen.controller.thymeleafConroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.facade.UserFacade;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class DisplayUserSettingsController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/settings")
    public String getSettings(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user",userFacade.findUserById(user.getId()));
        return "user/settings";
    }
}
