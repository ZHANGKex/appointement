package com.isep.appointement.Repository;

import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Patient;
import com.isep.appointement.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT d from Doctor d where d.mail = ?1")
    Optional<Doctor> findDocByMail(String email);
    @Query("SELECT d from Doctor d where d.name = ?1")
    Optional<Doctor> findDocByName(String name);

    @Query("SELECT p from Patient p where p.mail = ?1")
    Optional<Patient> findPatientsByMail(String email);
    @Query("SELECT p from Patient p where p.name = ?1")
    Optional<Patient> findPatientsByName(String name);

    @Query("SELECT r from Reservation r where r.status = ?1")
    Page<Reservation> findByStatus(String status, Pageable pageable);
    @Query("SELECT r from Reservation r where r.patient = ?1")
    Page<Reservation> findByPatient(Patient patient, Pageable pageable);
    @Query("SELECT r from Reservation r where r.doctor = ?1")
    Page<Reservation> findByDoctor(Doctor doctor, Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE r.doctorName LIKE %:keyword% OR r.patientName LIKE %:keyword% OR r.appointmentTime LIKE %:keyword% OR r.status LIKE %:keyword%")
    Page<Reservation> findByKeyword(String keyword, Pageable pageable);
    @Query("SELECT r FROM Reservation r WHERE r.patient = :patient and(r.doctorName LIKE %:keyword% OR r.patientName LIKE %:keyword% OR r.appointmentTime LIKE %:keyword% OR r.status LIKE %:keyword%)")
    Page<Reservation> findByKeywordPatient(String keyword, Pageable pageable, Patient patient);
    @Query("SELECT r FROM Reservation r WHERE r.doctor = :doctor and (r.doctorName LIKE %:keyword% OR r.patientName LIKE %:keyword% OR r.appointmentTime LIKE %:keyword% OR r.status LIKE %:keyword%)")
    Page<Reservation> findByKeywordDoctor(String keyword, Pageable pageable, Doctor doctor);



    @Transactional
    @Modifying
    @Query("UPDATE Reservation r " +
            "SET r.status = 'enabled' WHERE r.id = ?1")
    int enableAppointment(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Reservation r " +
            "SET r.status = 'canceled' WHERE r.id = ?1")
    int cancelAppointment(Long id);
}
