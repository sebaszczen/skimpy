package pl.sebaszczen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectSwagger {

    @GetMapping("/sw")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html#/";
    }
}
