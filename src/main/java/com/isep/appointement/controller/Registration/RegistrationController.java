package com.isep.appointement.controller.Registration;

import com.isep.appointement.controller.email.EmailValidator;
import com.isep.appointement.controller.patient.PatientService;
import com.isep.appointement.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(("/register"))
public class RegistrationController {

    private PatientService patientService;
    private EmailValidator emailValidator;


    public RegistrationController(PatientService patientService,EmailValidator emailValidator) {
        this.patientService = patientService;
        this.emailValidator = emailValidator;
    }


    @PostMapping
    public String RegisterPatient(@ModelAttribute("patient") Patient patient){
        Boolean isValidMail = emailValidator.test(patient.getMail());
        if(!isValidMail){throw new IllegalStateException("emial not valid");}
        patientService.addPatient(patient);

        return "redirect:/register?success";
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return patientService.confirmToken(token);
    }
    @GetMapping
    public String addPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "Registration";
    }

}
