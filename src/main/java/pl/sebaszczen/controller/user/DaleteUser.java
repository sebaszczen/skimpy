package pl.sebaszczen.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.facade.UserFacade;

import java.util.List;

@Controller
@RequestMapping("/user")
public class DaleteUser {

    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = {"/delete"})
    public String delete(Model model, @RequestParam Long id) {
        userFacade.deleteUser(id);
        List<User> userList = userFacade.findAll();
        model.addAttribute("userList", userList);
        return "saved";
    }
}
