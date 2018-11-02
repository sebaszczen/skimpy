package pl.sebaszczen.controller.thymeleafConroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.user.User;
import pl.sebaszczen.domain.user.UserDto;
import pl.sebaszczen.facade.EmailFacade;
import pl.sebaszczen.facade.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class SaveUserController {

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
        if (user == null) {
            bindingResult.rejectValue("email", "message.regError", "There is an account with that email address: "
                    + userDto.getEmail());
            return "save";
        } else {
            boolean success = emailFacade.activateToken(user, request);
            if (!success) {
                return "redirect:/login?registerFail";
            } else {
                return "redirect:/login?registerSuccess";
//            }
            }
        }
    }
    }
