/*
package com.isep.appointement.controller.doctor;

import com.isep.appointement.controller.patient.PatientService;
import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Patient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/hello")
    public List<Doctor> patient(Long id){
        return doctorService.getAllPatient();
    }

*/
/*    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient){

        patientService.addPatient(patient);
    }*//*

    @GetMapping("/doctor")
    public String showPatient(Model model){
*/
/*        String role = patient.getRole().name();
        if(role != Roles.ADMIN.name()){
            return "redirect:/home";
        }*//*

        model.addAttribute("patients", doctorService.getAllPatient());
        return "doctor";
    }

    @GetMapping("/doctor/new")
    public String addPatient(Model model){

        model.addAttribute("doctor", new Doctor());

        return "addPatient";
    }
    @GetMapping("/doctor/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model){
        model.addAttribute("doctor", doctorService.getPatientById(id));

        return "edit_Patient";
    }
    @PostMapping("/doctor")
    public String savePatient(@ModelAttribute("doctor") Doctor doctor){
        doctorService.add(doctor);

        return "redirect:/doctor/new?success";
    }
    @PostMapping("/doctor/{id}")
    public String UpdatePatient(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor, Model model){
        Doctor existingDoctor = doctorService.getById(id);
        existingDoctor.setIdDoc(id);
        existingDoctor.setName(doctor.getName());
        existingDoctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        existingDoctor.setTelephone(doctor.getTelephone());
        existingDoctor.setMail(doctor.getMail());
        existingDoctor.setIdNumber(doctor.getIdNumber());
        existingDoctor.setBirthday(doctor.getBirthday());
        existingDoctor.setAge(doctor.getAge());
        existingDoctor.setSex(doctor.getSex());

        doctorService.editPatient(existingDoctor);
        return "redirect:/doctor";
    }

    @GetMapping("/doctor/{id}")
    public String RemoveDoctor(@PathVariable Long id){
        doctorService.deletePatient(id);
        return "redirect:/doctor";
    }

}
*/
