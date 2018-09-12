package pl.sebaszczen.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public String doctorAddSubmit(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "save";
        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            List<User> userList = userService.findAll();
            model.addAttribute("userList", userList);
            return "saved";
        }
    }

    @GetMapping("/saveform")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "save";
    }

    @GetMapping("/logged")
    public String logged() {
        return "logged";
    }
}
