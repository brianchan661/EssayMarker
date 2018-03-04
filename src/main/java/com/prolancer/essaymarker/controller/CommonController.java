package com.prolancer.essaymarker.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.prolancer.essaymarker.model.view.SignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping(path = "index", method = GET)
    public String home() {
        return "index";
    }

    @RequestMapping(path = "login", method = GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "signup", method = GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(path = "signup", method = POST)
    public String signup(@ModelAttribute @Validated SignUp signUp, BindingResult result, Model model) {
        if (result.hasErrors()){
            System.out.println(result.getErrorCount());
        }

        System.out.println(signUp.getEmail());
        return "register_finish";
    }
}
