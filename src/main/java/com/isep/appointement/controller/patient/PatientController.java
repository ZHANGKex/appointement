package com.isep.appointement.controller.patient;

import com.isep.appointement.Repository.PatientRepository;
import com.isep.appointement.model.Patient;
import com.isep.appointement.model.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/patient")
    public String getPatientsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,Model model) {
        model.addAttribute("patients", patientService.getPatientsByPageAndKeyword(page,size,keyword));
/*        model.addAttribute("currentPage", patients.getNumber());
        model.addAttribute("totalPages", patients.getTotalPages());*/
        return "patient";
    }
    @GetMapping("/info/patient")
    public String getPatient(Model model, Principal principal) {
        model.addAttribute("patient", patientService.getPatientByEmail(principal.getName()));

        return "patientShow";
    }

    @GetMapping("/patient/new")
    public String addPatient(Model model){

        model.addAttribute("patient", new Patient());

        return "addPatient";
    }

    @PostMapping("/patient")
    public String savePatient(@ModelAttribute("patient") Patient patient){
        patientService.addPatient(patient);

        return "redirect:/patient/new?success";
    }
    @GetMapping("/patient/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model){
        model.addAttribute("patient", patientService.getPatientById(id));

        return "edit_Patient";
    }
    @PostMapping("/patient/{id}")
    public String UpdatePatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient, Model model){
        Patient existingPatient = patientService.getPatientById(id);
        existingPatient.setId(id);
        existingPatient.setName(patient.getName());
        existingPatient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        existingPatient.setTelephone(patient.getTelephone());
        existingPatient.setMail(patient.getMail());
        existingPatient.setIdNumber(patient.getIdNumber());
        existingPatient.setBirthday(patient.getBirthday());
        existingPatient.setAge(patient.getAge());
        existingPatient.setSex(patient.getSex());
        existingPatient.setRole(patient.getRole());

        patientService.editPatient(existingPatient);
        return "redirect:/patient";
    }
    @PostMapping("/info/patient/{id}")
    public String saveChange(@PathVariable Long id, @ModelAttribute("patient") Patient patient, Model model){
        Patient existingPatient = patientService.getPatientById(id);
        existingPatient.setName(patient.getName());
        existingPatient.setTelephone(patient.getTelephone());
        existingPatient.setMail(patient.getMail());
        existingPatient.setBirthday(patient.getBirthday());
        existingPatient.setAge(patient.getAge());
        existingPatient.setJob(patient.getJob());
        existingPatient.setSex(patient.getSex());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setAllergens(patient.getAllergens());
        existingPatient.setChronicDiseases(patient.getChronicDiseases());
        existingPatient.setGeneticDiseases(patient.getGeneticDiseases());

        patientService.editPatient(existingPatient);
        return "redirect:/info/patient";
    }
    @GetMapping("/info/patient/edit/{id}")
    public String ModifyInfo(@PathVariable Long id, Model model){
        model.addAttribute("patient", patientService.getPatientById(id));

        return "patientEdit";
    }
    @GetMapping("/patient/{id}")
    public String RemovePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "redirect:/patient";
    }

}
