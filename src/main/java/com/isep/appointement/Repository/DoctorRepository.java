package com.isep.appointement.Repository;

import com.isep.appointement.model.Department;
import com.isep.appointement.model.Doctor;
import com.isep.appointement.model.Hospital;
import com.isep.appointement.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d from Doctor d where d.mail = ?1")
    Optional<Doctor> findByMail(String email);
    @Query("SELECT d from Doctor d where d.telephone = ?1")
    Optional<Doctor> findByPhone(String phone);
    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:keyword% OR d.mail LIKE %:keyword% OR d.deptName LIKE %:keyword% OR d.birthday LIKE %:keyword%")
    Page<Doctor> findByKeyword(String keyword, Pageable pageable);

    @Query("SELECT dept from Department dept where dept.name = ?1")
    Optional<Department> findDeptByName(String name);

    @Query("SELECT h FROM Hospital h where h.id = (SELECT d.hospital from Doctor d where d.idDoc = :id)")
    Optional<Hospital> findHosOfDoc(Long id);
/*    @Modifying
    @Query("UPDATE Doctor d " +
            "SET d.enabled = TRUE, d.locked = true WHERE d.mail = ?1")
    int enablePatient(String email);*/
}
