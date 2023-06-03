package com.isep.appointement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class homeController {


    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/home")
    public String index() {
        return "home";
    }

    @GetMapping("/doctor")
    public String doctor() {
        return "医生";
    }

    @GetMapping("/announcement")
    public String announcement() {
        return "announcement";
    }

    @GetMapping("/science")
    public String science() {
        return "科普";
    }

    @GetMapping("/pretriage")
    public String pretriage(){
        return "Pre_Triage";
    }

    @RequestMapping("/signup")
    public String test(Model model){
        model.addAttribute("msg", "hello, please Signup");
        return "signup";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
