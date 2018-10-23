package pl.sebaszczen.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.facade.UserFacade;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class DisplayUserSettings {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/settings")
    public String getSettings(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user",userFacade.findUserById(user.getId()));
        return "user/settings";
    }
}
