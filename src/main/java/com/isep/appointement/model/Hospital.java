package com.isep.appointement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hospital")
@AllArgsConstructor
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Doctor> doctors;
    @ManyToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Department> departments;

    public Hospital() {
    }

    public Hospital(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
