package pl.sebaszczen.controller.thymeleafConroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.facade.UserFacade;

@Controller
@RequestMapping("/user")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UpdateUser {

//    public UpdateUser(UserFacade userFacade) {
//        System.out.println("new update user");
//    }

    @Autowired
    UserFacade userFacade;

    @GetMapping("/update{id}")
    public String updateUser(@RequestParam Long id, Model model) {
        User userById = userFacade.findUserById(id);

        model.addAttribute("user", userById);
        return "user/updateUser";
    }

    @PostMapping("/updated")
    public String updated(@ModelAttribute(value = "user") User user,@ModelAttribute(value = "x") String color, Model model) {
        userFacade.updateUser(user);
        model.addAttribute("userr",user);
        return "user/settings";
    }
}
