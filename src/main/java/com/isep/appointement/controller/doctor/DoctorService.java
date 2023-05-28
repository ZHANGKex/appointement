package com.isep.appointement.controller.doctor;

import com.isep.appointement.Repository.DoctorRepository;
import com.isep.appointement.model.ConfirmationToken;
import com.isep.appointement.controller.ConfirmToken.ConfirmationTokenService;
import com.isep.appointement.controller.email.EmailSender;
import com.isep.appointement.model.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
@AllArgsConstructor
public class DoctorService implements UserDetailsService {

    private final DoctorRepository doctorRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private EmailSender emailSender;
    public static String LoginErrorMsg;


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).get();
    }
    public Doctor getDoctorByEmail(String email) {
        Optional<Doctor> doctorsByEmail = doctorRepository.findDoctorsByMail(email.toLowerCase(Locale.ROOT));
        if(!doctorsByEmail.isPresent() && !doctorsByEmail.get().getEnabled()){
            throw new IllegalStateException("doctor email does not exist ");
        }
        return doctorRepository.findDoctorsByMail(email.toLowerCase(Locale.ROOT)).get();
    }
    public Doctor getDoctorByPhone(String telephone) {
        Optional<Doctor> doctorsByPhone = doctorRepository.findDoctorsByPhone(telephone);
        if(!doctorsByPhone.isPresent()){
            throw new IllegalStateException("doctor phone number does not exist ");
        }
        return doctorRepository.findDoctorsByPhone(telephone).get();
    }

    public String addDoctor(Doctor doctor) {
        Optional<Doctor> doctorsByMail = doctorRepository.findDoctorsByMail(doctor.getMail());
        if(doctorsByMail.isPresent() && !doctorsByMail.get().getEnabled()){
            throw new IllegalStateException("email existed ");
        }

        doctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        doctorRepository.save(doctor);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                doctor
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send email;
        String link = "http://localhost:8080/register/confirm?token=" + token;
        emailSender.send(
                doctor.getMail(),
                buildEmail(doctor.getName(), link));
        return token;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getDoctor().getMail());
        return "redirect:/home";
    }
    public void editDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findDoctorsByMail(username).get();
        if(doctor ==null){
            LoginErrorMsg = "This email doesn't exist";
            throw new UsernameNotFoundException("Invalid email or password");
        }
        else if(!doctor.isEnabled()){
            LoginErrorMsg = "This email hasn't activated";
            throw new UsernameNotFoundException("This email hasn't activated");
        }

        return new org.springframework.security.core.userdetails.User(doctor.getMail(),
                doctor.getPassword(),doctor.isEnabled(),doctor.isAccountNonExpired(),
                doctor.isCredentialsNonExpired(),doctor.isAccountNonLocked(),
                doctor.getAuthorities());
    }

    public int enableUser(String email) {
        return doctorRepository.enableDoctor(email);
    }

    private String buildEmail(String name, String link) {
        // Keep this the same
    }
}
