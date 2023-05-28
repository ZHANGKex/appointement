package com.isep.appointement.controller.doctor;

import com.isep.appointement.model.Doctor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(com.isep.appointement.controller.doctor.DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/hello")
    public List<Doctor> doctor(Long id){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor")
    public String showDoctor(Model model){
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor";
    }

    @GetMapping("/doctor/new")
    public String addDoctor(Model model){
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }

    @GetMapping("/doctor/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model){
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "edit_Doctor";
    }

    @PostMapping("/doctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor){
        doctorService.addDoctor(doctor);
        return "redirect:/doctor/new?success";
    }

    @PostMapping("/doctor/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor, Model model){
        Doctor existingDoctor = doctorService.getDoctorById(id);
        existingDoctor.setIdDoc(id);
        existingDoctor.setName(doctor.getName());
        existingDoctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        existingDoctor.setTelephone(doctor.getTelephone());
        existingDoctor.setMail(doctor.getMail());
        existingDoctor.setBirthday(doctor.getBirthday());
        existingDoctor.setAge(doctor.getAge());
        existingDoctor.setSex(doctor.getSex());

        doctorService.editDoctor(existingDoctor);
        return "redirect:/doctor";
    }

    @GetMapping("/doctor/{id}")
    public String removeDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctor";
    }
}
