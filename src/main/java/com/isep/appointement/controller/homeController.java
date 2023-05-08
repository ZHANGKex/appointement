package com.isep.appointement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class homeController {

/*    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }*/
    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/pretriage")
    public String pretriage(){
        return "Pre_Triage";
    }
}
