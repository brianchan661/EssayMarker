package com.prolancer.essaymarker.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.event.OnRegistrationCompleteEvent;
import com.prolancer.essaymarker.model.view.CommonMessage;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        return "login";
    }

    @RequestMapping(path = "login", method = POST)
    public String loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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
            return returnMessage(model, "invalid input");
        }
        UserInfo registeredUser = userService.createNewUser(signUp);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (registeredUser));
        return returnMessage(model, "A confirmation email is sent to your email address");
    }

    @RequestMapping(path = "signUpCompleted", method = GET)
    public String signUpCompleted(@RequestParam("verificationToken") String verToken, Model model) {
        if (userService.confirmVerificationToken(verToken)) {
            return returnMessage(model, "registration completed");
        }

        return returnMessage(model, "invalid verification token");
    }

    private String returnMessage(Model model, String message) {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(message);
        model.addAttribute("commonMessage", commonMessage);
        return "message";
    }
}
