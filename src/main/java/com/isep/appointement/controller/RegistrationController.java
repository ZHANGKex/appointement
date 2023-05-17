package com.isep.appointement.controller;

import com.isep.appointement.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("/register"))
public class RegistrationController {

    private PatientService patientService;

    public RegistrationController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public String RegisterPatient(@ModelAttribute("patient") Patient patient){
        patientService.addPatient(patient);

        return "redirect:/register?success";
    }

    @GetMapping
    public String addPatient(Model model){
        model.addAttribute("patient", new Patient());

        return "register";
    }

}
