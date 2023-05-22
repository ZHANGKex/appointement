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
public class TimeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    public TimeLine() {
    }


    @Override
    public String toString() {
        return startTime.getHour()+ " " + startTime.getMinute() + " - " + endTime.getHour()+ " " + endTime.getMinute();
    }
    public String getDate(){
        return startTime.toLocalDate().toString();
    }

}
