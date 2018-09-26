package pl.sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sebaszczen.domain.User;
import pl.sebaszczen.domain.UserDto;
import pl.sebaszczen.domain.activateAccount.AccountActivateToken;
import pl.sebaszczen.domain.resetPassword.Mail;
import pl.sebaszczen.repository.AccountActivateTokenRepository;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.UserService;
import pl.sebaszczen.services.resetPAssword.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AccountActivateTokenRepository accountActivateTokenRepository;

    @Autowired
    EmailService emailService;

    @PostMapping("/save")
    public String doctorAddSubmit(@ModelAttribute("user") @Valid UserDto userDto,
                                  BindingResult bindingResult,  HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "save";
        }
        User user = createUserAccount(userDto);
        if (user==null){
//            bindingResult.rejectValue("email", "message.regError");
            bindingResult.rejectValue("email", "message.regError","There is an account with that email address: "
                    + userDto.getEmail());
            return "save";
        }

        else {
//            List<User> userList = userService.findAll();
//            model.addAttribute("userList", userList);
//            return "saved";
            activateToken(user,request);
            return "redirect:/login?registerSuccess";
        }
    }

    private void activateToken(User user, HttpServletRequest request) {
        AccountActivateToken token = new AccountActivateToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        accountActivateTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Activate your account and anjoy skimpy :)");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skimpy.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/activate-account?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
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
        model.addAttribute("user", new UserDto.Builder().build());
        return "save";
    }

}
