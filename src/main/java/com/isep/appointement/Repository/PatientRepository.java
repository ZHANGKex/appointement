package com.isep.appointement.Repository;

import com.isep.appointement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p from Patient p where p.mail = ?1")
    Optional<Patient> findPatientsByMail(String email);
    @Query("SELECT p from Patient p where p.telephone = ?1")
    Optional<Patient> findPatientsByPhone(String phone);


    @Modifying
    @Query("UPDATE Patient p " +
            "SET p.enabled = TRUE, p.locked = true WHERE p.mail = ?1")
    int enablePatient(String email);
}
