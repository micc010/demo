package com.github.rogerli.system;

import com.github.rogerli.framework.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController extends AbstractController{

    @RequestMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String hello() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}