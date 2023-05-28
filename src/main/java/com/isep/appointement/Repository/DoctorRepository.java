package com.isep.appointement.Repository;

import com.isep.appointement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d from Doctor d where d.mail = ?1")
    Optional<Doctor> findDoctorsByMail(String email);

    @Query("SELECT d from Doctor d where d.telephone = ?1")
    Optional<Doctor> findDoctorsByPhone(String phone);

    @Transactional
    @Modifying
    @Query("UPDATE Doctor d " +
            "SET d.enabled = TRUE, d.locked = true WHERE d.mail = ?1")
    int enableDoctor(String email);
}
