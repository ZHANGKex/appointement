package com.isep.appointement.controller.patient;

import com.isep.appointement.model.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomPatientDetails implements UserDetails {

    private Patient patient;

    public CustomPatientDetails(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define the patient's authorities/roles here
        return patient.getAuthorities();
    }

    @Override
    public String getPassword() {
        return patient.getPassword();
    }

    @Override
    public String getUsername() {
        return patient.getMail();
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
        return patient.getEnabled();
    }

    // Implement other UserDetails methods as needed

    // Additional methods to retrieve patient-specific details
    public Long getPatientId() {
        return patient.getId();
    }

    public String getName() {
        return patient.getName();
    }


    // Add more methods to retrieve other patient-specific details
}
