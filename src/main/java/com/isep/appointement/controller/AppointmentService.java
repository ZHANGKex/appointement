package com.isep.appointement.controller;

import com.isep.appointement.Repository.AppointmentRepository;
import com.isep.appointement.Repository.PatientRepository;
import com.isep.appointement.model.AppointmentStatus;
import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Patient;
import com.isep.appointement.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public List<Reservation> getAllAppointment() {
        return appointmentRepository.findAll();
    }
    public Page<Reservation> getAppointmentsByPageAndKeyword(int page, int size, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return appointmentRepository.findByKeyword(keyword, PageRequest.of(page, size));
        } else {
            return appointmentRepository.findAll(PageRequest.of(page, size));
        }
    }
    public Page<Reservation> getAppointmentsPatient(int page, int size, String keyword, Patient patient) {
        if (keyword != null && !keyword.isEmpty()) {
            return appointmentRepository.findByKeywordPatient(keyword, PageRequest.of(page, size), patient);
        } else {
            return appointmentRepository.findByPatient(patient, PageRequest.of(page, size));
        }
    }
    public Page<Reservation> getAppointmentsDoctor(int page, int size, String keyword, Doctor doctor) {
        if (keyword != null && !keyword.isEmpty()) {
            return appointmentRepository.findByKeywordDoctor(keyword, PageRequest.of(page, size), doctor);
        } else {
            return appointmentRepository.findByDoctor(doctor, PageRequest.of(page, size));
        }
    }
    public Doctor findDoctorbyName(String name){
       return appointmentRepository.findDocByName(name).get();
    }
    public Patient findPatientbyName(String name){
        Patient patient = appointmentRepository.findPatientsByName(name).get();
        if(patient != null)
        return patient;
        else return null;
    }
    public Reservation findAppointmentById(Long id){
        return appointmentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("appointment not found"));
    }
    public Page<Reservation> findByStatus(String status,int page, int size){
        return appointmentRepository.findByStatus(status, PageRequest.of(page,size));
    }

    public Reservation addAppointment(Reservation reservation) {
        reservation.setPatient(findPatientbyName(reservation.getPatientName()));
        reservation.setDoctor(findDoctorbyName(reservation.getDoctorName()));
        reservation.setStatus(AppointmentStatus.pending);
        return appointmentRepository.save(reservation);
    }
    public void editAppointment(Reservation reservation) {
        appointmentRepository.save(reservation);
    }
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    public int cancelAppointment(Long id) {
        return appointmentRepository.cancelAppointment(id);
    }
    public int acceptAppointment(Long id) {
        return appointmentRepository.enableAppointment(id);
    }
}
