package pl.sebaszczen.controller.thymeleafConroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.facade.UserFacade;

@Controller
@RequestMapping("/user")
public class UpdateUser {

    @Autowired
    UserFacade userFacade;

    @GetMapping("/update{id}")
    public String updateUser(@RequestParam Long id, Model model) {
        User userById = userFacade.findUserById(id);

        model.addAttribute("user", userById);
        return "user/updateUser";
    }

    @PostMapping("/updated")
    public String updated(@ModelAttribute(value = "user") User user, Model model) {
        userFacade.updateUser(user);
        model.addAttribute("userr",user);
        return "user/settings";
    }
}
