package pl.sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public String doctorAddSubmit(Model model, @ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Errors errors) {
        if (bindingResult.hasErrors()) {
            return "save";
        }
        if (createUserAccount(userDto)==null){
//            bindingResult.rejectValue("email", "message.regError");
            bindingResult.rejectValue("email", "message.regError","There is an account with that email address: "
                    + userDto.getEmail());
            return "save";
        }

        else {
            List<User> userList = userService.findAll();
            model.addAttribute("userList", userList);
            return "saved";
        }
    }

    private User createUserAccount(UserDto accountDto) {
        User registered = null;
        try {
            registered = userService.save(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

//    @RequestMapping(value = "user/delete_user/{id}", method = RequestMethod.GET)
//    public String handleDeleteUser(Model model, @PathVariable Long personId) {
//        userService.deleteUser(personId);
//        System.out.println(personId);
//        System.out.println("test");
//        List<User> userList = userService.findAll();
//        model.addAttribute("userList", userList);
//        return "saved";
//    }

    @GetMapping(value = {"/delete"})
    public String delete(Model model, @RequestParam Long id) {
        userService.deleteUser(id);
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "saved";
    }

    @GetMapping("/saveform")
    public String saveUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "save";
    }

}
