package com.isep.appointement.Repository;

import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d from Doctor d where d.mail = ?1")
    Optional<Doctor> findByMail(String email);
    @Query("SELECT d from Doctor d where d.telephone = ?1")
    Optional<Doctor> findByPhone(String phone);


/*    @Modifying
    @Query("UPDATE Doctor d " +
            "SET d.enabled = TRUE, d.locked = true WHERE d.mail = ?1")
    int enablePatient(String email);*/
}
