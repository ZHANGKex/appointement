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

import java.util.List;


@Controller
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

/*    @GetMapping("/hello")
    public List<Patient> patient(Long id){
        return patientService.getAllPatient();
    }*/

/*    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient){

        patientService.addPatient(patient);
    }*/
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

    @GetMapping("/patient/{id}")
    public String RemovePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "redirect:/patient";
    }

}
