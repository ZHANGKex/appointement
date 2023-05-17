package com.isep.appointement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @GetMapping("/home")
    public String index() {
        return "index";
    }
/*

    @GetMapping("/register")
    public String register() {
        return "register";
    }
*/

    @GetMapping("/pretriage")
    public String pretriage(){
        return "Pre_Triage";
    }

    @RequestMapping("/signup")
    public String test(Model model){
        model.addAttribute("msg", "hello, please Signup");
        return "signup";
    }
}
