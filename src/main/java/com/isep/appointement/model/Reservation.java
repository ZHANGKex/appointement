package com.isep.appointement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private int id;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "timeline"
    )
    private TimeLine appointmentTime;

    @Column(nullable = false)
    private Doctor doctor;
    @Column(nullable = false)
    private Patient patient;


    private String location;

    @Column(nullable = false)
    private String status;



    public Reservation() {
    }




}
