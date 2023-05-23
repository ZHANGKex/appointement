package com.isep.appointement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentController {
    @GetMapping("/appointment")
    public String appointment() {
        return "appointment";
    }
}
