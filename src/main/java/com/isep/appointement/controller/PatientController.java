package com.isep.appointement.controller;

import com.isep.appointement.model.Patient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/hello")
    public List<Patient> patient(){

        return patientService.getAllPatient() ;
    }

/*    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient){

        patientService.addPatient(patient);
    }*/
    @GetMapping("/patient")
    public String showPatient(Model model){
        model.addAttribute("patients", patientService.getAllPatient());
        return "patient";
    }

    @GetMapping("/patient/new")
    public String addPatient(Model model){
        model.addAttribute("patient", new Patient());

        return "addPatient";
    }
    @GetMapping("/patient/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model){
        model.addAttribute("patient", patientService.getPatientById(id));

        return "edit_Patient";
    }
    @PostMapping("/patient")
    public String savePatient(@ModelAttribute("patient") Patient patient){
        patientService.addPatient(patient);

        return "redirect:/patient/new?success";
    }
    @PostMapping("/patient/{id}")
    public String UpdatePatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient, Model model){
        Patient existingPatient = patientService.getPatientById(id);
        existingPatient.setId(id);
        existingPatient.setName(patient.getName());
        existingPatient.setUsername(patient.getUsername());
        existingPatient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        existingPatient.setTelephone(patient.getTelephone());
        existingPatient.setMail(patient.getMail());
        existingPatient.setIdNumber(patient.getIdNumber());
        existingPatient.setAge(patient.getAge());
        existingPatient.setSex(patient.getSex());

        patientService.editPatient(existingPatient);
        return "redirect:/patient";
    }

    @GetMapping("/patient/{id}")
    public String RemovePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "redirect:/patient";
    }

}
