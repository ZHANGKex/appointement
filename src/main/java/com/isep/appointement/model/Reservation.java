package com.isep.appointement.model;

import com.isep.appointement.Repository.AppointmentRepository;
import com.isep.appointement.controller.AppointmentService;
import com.isep.appointement.controller.Registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @Column(nullable = false)
    private String doctorName;
    @Column(nullable = false)
    private String patientName;
    @Column
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    public String getAppointmentTime() {
/*        if(appointmentTime != null){
            LocalDateTime localDateTime = LocalDateTime.parse(appointmentTime);
            return localDateTime.getHour() + ":" + localDateTime.getMinute() + " - " + localDateTime.plusHours(1).getHour()+ " " + localDateTime.plusHours(1).getMinute();
        }*/
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Reservation() {
    }

}
