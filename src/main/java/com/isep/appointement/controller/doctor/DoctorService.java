package com.isep.appointement.controller.doctor;

import com.isep.appointement.Repository.DoctorRepository;
import com.isep.appointement.Repository.PatientRepository;
import com.isep.appointement.model.*;
import com.isep.appointement.controller.ConfirmToken.ConfirmationTokenService;
import com.isep.appointement.controller.email.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    public Page<Doctor> getDoctorsByPageAndKeyword(int page, int size, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return doctorRepository.findByKeyword(keyword, PageRequest.of(page, size));
        } else {
            return doctorRepository.findAll(PageRequest.of(page, size));
        }
    }
  public Doctor getDoctorById(Long id) {

        return doctorRepository.findById(id).get();
    }
    public Doctor getDoctorByEmail(String email) {
        Optional<Doctor> patientsByEmail =  doctorRepository.findByMail(email);
        if(!patientsByEmail.isPresent()){
            throw new IllegalStateException("doctor email does not exist ");
        }
        return doctorRepository.findByMail(email).get();
    }
    public Hospital getHospitalOfDoc(Doctor doctor){
        return doctorRepository.findHosOfDoc(doctor.getIdDoc()).get();
    }

  public Doctor addDoctor(Doctor doctor) {
      LocalDate birthdate = doctor.getBirthday();
      if(birthdate == null){
          birthdate = LocalDate.now();
      }
      int age = Period.between(birthdate,LocalDate.now()).getYears();
      doctor.setAge(age);
      doctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
      doctor.setRole(Roles.Doctor);

      return doctorRepository.save(doctor);
  }
    public void editDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByMail(username).get();
        if(doctor ==null){
            throw new UsernameNotFoundException("Invalid email or password");
        }

        return new org.springframework.security.core.userdetails.User(doctor.getMail(),
                doctor.getPassword(), doctor.getAuthorities());

    }
}
