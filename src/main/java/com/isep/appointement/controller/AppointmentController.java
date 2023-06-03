package com.isep.appointement.controller;

import com.isep.appointement.Repository.DoctorRepository;
import com.isep.appointement.controller.doctor.DoctorService;
import com.isep.appointement.controller.patient.PatientService;
import com.isep.appointement.model.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;

    //admin
    @GetMapping("/appointment")
    public String showAllAppointmentbyKeyword(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keywordApp,Model model){
        model.addAttribute("reservations", appointmentService.getAppointmentsByPageAndKeyword(page,size,keywordApp));
        return "Appointment";
    }
    @GetMapping("/appointment/pending")
    public String showPending(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,Model model){
        model.addAttribute("reservations", appointmentService.findByStatus(AppointmentStatus.pending.name(),page,size));
        return "Appointment";
    }
    @GetMapping("/appointment/new")
    public String addAppointment(Model model){
        model.addAttribute("reservation", new Reservation());
        return "add_appointment";
    }
    @PostMapping("/appointment")
    public String saveAppointment(@ModelAttribute("reservation") Reservation reservation){
        appointmentService.addAppointment(reservation);
        return "redirect:/appointment/new?success";
    }
    @GetMapping("/appointment/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model){
        model.addAttribute("reservation", appointmentService.findAppointmentById(id));
        return "edit_appointment";
    }
    @PostMapping("/appointment/{id}")
    public String UpdateAppointment(@PathVariable Long id, @ModelAttribute("reservation") Reservation reservation, Model model){
        Reservation existingReservation = appointmentService.findAppointmentById(id);
       existingReservation.setDoctorName(reservation.getDoctorName());
       existingReservation.setPatientName(reservation.getPatientName());
       existingReservation.setAppointmentTime(reservation.getAppointmentTime());
       existingReservation.setLocation(reservation.getLocation());
       existingReservation.setStatus(reservation.getStatus());
        appointmentService.addAppointment(existingReservation);
        return "redirect:/appointment";
    }
    @GetMapping("/appointment/{id}")
    public String RemoveAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment";
    }
    //patient
    @GetMapping("/appointment/patient")
    public String showAppointmentbyKeyword(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keywordPat,Model model, Principal principal){
        String email = principal.getName();
        Patient patient = patientService.getPatientByEmail(email);
        model.addAttribute("reservations", appointmentService.getAppointmentsPatient(page,size,keywordPat,patient));
        return "appointment_patient";
    }
/*    @GetMapping("/appointment/patient/{name}")
    public String showAppointment(@PathVariable String name, @ModelAttribute("patient") Patient patient, Model model){
        patient = patientService.getPatientByName(name);
        model.addAttribute("reservations", appointmentService.findByPatient(patient.getName()));
        return "appointment_patient";
    }*/
@GetMapping("/appointment/patient/search")
public String searchDoctor(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(required = false) String keywordDoc,Model model){

    model.addAttribute("doctors", doctorService.getDoctorsByPageAndKeyword(page, size, keywordDoc));
    return "AppointmentSearch";
}
    @GetMapping("/appointment/patient/confirm/{idDoc}")
    public String confirmAppointment(@PathVariable Long idDoc, Model model){
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("doctor", doctorService.getDoctorById(idDoc));
        return "AppointmentConfirm";
    }
    @PostMapping("/appointment/patient/new/{idDoc}")
    public String saveAppointmentPatient(@PathVariable Long idDoc, @ModelAttribute("reservation") Reservation reservation, Principal principal){
        Doctor doctor = doctorService.getDoctorById(idDoc);
        Patient patient = patientService.getPatientByEmail(principal.getName());
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setDoctorName(doctor.getName());
        reservation.setPatientName(patient.getName());
        reservation.setLocation(doctor.getHospital().getAddress());
        reservation.setStatus(AppointmentStatus.pending);
        appointmentService.addAppointment(reservation);
        return "redirect:/appointment/patient";
    }
    @PostMapping("/appointment/patient/{id}")
    public String UpdateAppointmentPatient(@PathVariable Long id, @ModelAttribute("reservation") Reservation reservation, Model model){
        Reservation existingReservation = appointmentService.findAppointmentById(id);
        existingReservation.setDoctorName(reservation.getDoctorName());
        existingReservation.setPatientName(reservation.getPatientName());
        existingReservation.setLocation(reservation.getLocation());
        existingReservation.setAppointmentTime(reservation.getAppointmentTime());
        appointmentService.editAppointment(existingReservation);
        return "redirect:/appointment/patient";
    }
    @GetMapping("/appointment/patient/edit/{id}")
    public String editAppointmentPatient(@PathVariable Long id, Model model){
        model.addAttribute("reservation", appointmentService.findAppointmentById(id));
        return "edit_appointment_patient";
    }
    @GetMapping("/appointment/patient/{id}")
    public String CancelAppointmentPatient(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return "redirect:/appointment/patient";
    }
    //doctor
    @GetMapping("/appointment/doctor")
    public String showDoctorAppointment
    (@RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "10") int size,
     @RequestParam(required = false) String keywordDoc,Model model, Principal principal){
        String email = principal.getName();
        Doctor doctor = doctorRepository.findByMail(email).get();
        model.addAttribute("reservations", appointmentService.getAppointmentsDoctor(page,size,keywordDoc, doctor));
        return "appointment_doctor";
    }
    @GetMapping("/appointment/doctor/accept/{id}")
    public String AcceptAppointment(@PathVariable Long id){
        appointmentService.acceptAppointment(id);
        return "redirect:/appointment/doctor";
    }
    @GetMapping("/appointment/doctor/{id}")
    public String CancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return "redirect:/appointment/doctor";
    }

}
