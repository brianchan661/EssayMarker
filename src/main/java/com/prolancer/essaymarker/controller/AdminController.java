package com.prolancer.essaymarker.controller;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.prolancer.essaymarker.model.view.CommonMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(path = "test", method = GET)
    public String test(Model model) {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage("testing avs");
        model.addAttribute("commonMessage", commonMessage);
        return "message";
    }
}
