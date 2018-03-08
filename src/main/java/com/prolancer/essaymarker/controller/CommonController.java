package com.prolancer.essaymarker.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.event.OnRegistrationCompleteEvent;
import com.prolancer.essaymarker.model.view.SignUp;
import com.prolancer.essaymarker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(path = "index", method = GET)
    public String home() {
        return "index";
    }

    @RequestMapping(path = "login", method = GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.print(auth.getPrincipal());
        System.out.print(LocalDateTime.now());
        return "login";
    }

    @RequestMapping(path = "login", method = POST)
    public String loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.print(auth.getPrincipal());
        return "login";
    }

    @RequestMapping(path = "signup", method = GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(path = "signup", method = POST)
    public String signup(@ModelAttribute @Validated SignUp signUp, BindingResult result,
                         WebRequest request, Model model) {
        if (result.hasErrors()){
            System.out.println(result.getErrorCount());
        }
        UserInfo registeredUser = userService.createNewUser(signUp);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (registeredUser, request.getContextPath()));
        return "register_finish";
    }
}
