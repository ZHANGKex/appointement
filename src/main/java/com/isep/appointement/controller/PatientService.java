package com.isep.appointement.controller;

import com.isep.appointement.Repository.PatientRepository;
import com.isep.appointement.model.Patient;
import com.isep.appointement.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements UserDetailsService {
    @Autowired
    private final PatientRepository patientRepository;

    private Roles role;

/*    @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatient() {

        return patientRepository.findAll();
    }
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id).get();
    }
    public Patient getPatientByEmail(String email) {
        Optional<Patient> patientsByEmail =  patientRepository.findPatientsByMail(email);
        if(!patientsByEmail.isPresent()){
            throw new IllegalStateException("patient email does not exist ");
        }
        return patientRepository.findPatientsByMail(email).get();
    }
    public Patient getPatientByPhone(String telephone) {
        Optional<Patient> patientsByPhone =  patientRepository.findPatientsByPhone(telephone);
        if(!patientsByPhone.isPresent()){
            throw new IllegalStateException("patient phone number does not exist ");
        }
        return patientRepository.findPatientsByMail(telephone).get();
    }

    public void addPatient(Patient patient) {
        Optional<Patient> patientsByMail =  patientRepository.findPatientsByMail(patient.getMail());
        if(patientsByMail.isPresent()){
            throw new IllegalStateException("email existed ");
        }
        patient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        patientRepository.save(patient);
    }

    public void editPatient(Patient patient) {
        patientRepository.save(patient);
    }
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findPatientsByMail(username).get();
        if(patient ==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(patient.getMail(), patient.getPassword(), mapRolesToAuthorities());

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Roles.Patient.name());
        return Collections.singletonList(authority);
    }
}
