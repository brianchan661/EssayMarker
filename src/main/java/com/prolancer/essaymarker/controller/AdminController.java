package com.prolancer.essaymarker.controller;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(path = "test", method = GET)
    public String test() {
        return "test";
    }
}
