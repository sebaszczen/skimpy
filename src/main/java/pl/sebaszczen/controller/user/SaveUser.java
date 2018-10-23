package pl.sebaszczen.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.facade.EmailFacade;
import pl.sebaszczen.facade.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SaveUser {

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private EmailFacade emailFacade;

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid UserDto userDto,
                           BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "save";
        }
        User user = userFacade.createUserAccount(userDto);
        if (user==null){
            bindingResult.rejectValue("email", "message.regError","There is an account with that email address: "
                    + userDto.getEmail());
            return "save";
        }
        else {
            emailFacade.activateToken(user,request);
            return "redirect:/login?registerSuccess";
        }
    }



//    @GetMapping("/saveform")
//    public String saveUser(Model model) {
//        model.addAttribute("user", new UserDto.Builder().build());
//        return "save";
//    }

}
