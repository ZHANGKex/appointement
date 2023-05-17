package com.isep.appointement.Repository;

import com.isep.appointement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p from Patient p where p.mail = ?1")
    Optional<Patient> findPatientsByMail(String email);
    @Query("SELECT p from Patient p where p.telephone = ?1")
    Optional<Patient> findPatientsByPhone(String phone);
}
