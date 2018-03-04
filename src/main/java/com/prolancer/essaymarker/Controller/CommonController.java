package com.prolancer.essaymarker.Controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping(path = "index", method = GET)
    public String home() {
        return "index";
    }

    @RequestMapping(path = "plans", method = GET)
    public String plans() {
        return "plans";
    }
}
