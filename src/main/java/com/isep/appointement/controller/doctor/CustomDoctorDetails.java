package com.isep.appointement.controller.doctor;

import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomDoctorDetails implements UserDetails {

    private Doctor doctor;

    public CustomDoctorDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define the doctor's authorities/roles here
        return doctor.getAuthorities();
    }

    @Override
    public String getPassword() {
        return doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Implement other UserDetails methods as needed

    // Additional methods to retrieve doctor-specific details
    public Long getDoctorId() {
        return doctor.getIdDoc();
    }

    public String getFullName() {
        return doctor.getName();
    }
}

