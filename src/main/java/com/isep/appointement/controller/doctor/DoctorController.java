package com.isep.appointement.controller.doctor;

import com.isep.appointement.model.Doctor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping("/login/doctor")
    public String showDoctorLoginPage() {
        return "Signin_doctor";
    }

    @GetMapping("/doctor")
    public String showDoctor(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String keyword,Model model){

        model.addAttribute("doctors", doctorService.getDoctorsByPageAndKeyword(page, size, keyword));
        return "doctor";
    }
    @GetMapping("/info/doctor")
    public String showDoctorInfo(Model model, Principal principal) {
        model.addAttribute("doctor", doctorService.getDoctorByEmail(principal.getName()));
        return "doctorInfo";
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
    public String UpdateAccount(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor, Model model){
        Doctor existingDoctor = doctorService.getDoctorById(id);
        existingDoctor.setIdDoc(id);
        existingDoctor.setName(doctor.getName());
        existingDoctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        existingDoctor.setTelephone(doctor.getTelephone());
        existingDoctor.setMail(doctor.getMail());
        existingDoctor.setBirthday(doctor.getBirthday());
        existingDoctor.setAge(doctor.getAge());
        existingDoctor.setSex(doctor.getSex());
        existingDoctor.setHospitalName(doctor.getHospitalName());
        doctorService.editDoctor(existingDoctor);
        return "redirect:/doctor";
    }
    @PostMapping("/info/doctor/save")
    public String UpdateInfo(@ModelAttribute("doctor") Doctor doctor, Model model, Principal principal){
        Doctor existingDoctor = doctorService.getDoctorByEmail(principal.getName());
        existingDoctor.setName(doctor.getName());
        existingDoctor.setTelephone(doctor.getTelephone());
        existingDoctor.setMail(doctor.getMail());
        existingDoctor.setBirthday(doctor.getBirthday());
        existingDoctor.setAge(doctor.getAge());
        existingDoctor.setSex(doctor.getSex());
        existingDoctor.setDeptName(doctor.getDeptName());
        existingDoctor.setHospitalName(doctor.getHospitalName());
        existingDoctor.setAvailableTimings(doctor.getAvailableTimings());
        existingDoctor.setEducationBackground(doctor.getEducationBackground());
        existingDoctor.setResume(doctor.getResume());
        existingDoctor.setTitle(doctor.getTitle());
        existingDoctor.setSpecialty(doctor.getSpecialty());

        doctorService.editDoctor(existingDoctor);
        return "redirect:/info/doctor";
    }
    @GetMapping("/doctor/{id}")
    public String RemoveDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctor";
    }

}
