package com.isep.appointement.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table
public class Doctor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int idDoc;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "birthday")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @Column(name = "age")
    private int age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "phone", nullable = false, length = 20)
    private int telephone;

    @Column(name = "email", length = 50)
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Roles role = Roles.Doctor;

    @Column(name = "education_background", nullable = false, length = 50)
    private String educationBackground;

    @Column(name = "specialty",nullable = false, length = 255)
    private String specialty;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "resume", length = 255)
    private String resume;

    @Column(name = "reception_requirements", length = 255)
    private String receptionRequirements;

    @Column(name = "available_timings", length = 255)
    private String availableTimings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


    public Doctor() {

    }

    public Doctor(int idDoc, String password, String name, int age, String sex, int telephone, String mail, String educationBackground, String specialty, String title, String resume, String receptionRequirements, String availableTimings) {
        this.idDoc = idDoc;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telephone = telephone;
        this.mail = mail;
        this.educationBackground = educationBackground;
        this.specialty = specialty;
        this.title = title;
        this.resume = resume;
        this.receptionRequirements = receptionRequirements;
        this.availableTimings = availableTimings;
    }

    //Attributes necessary

    public Doctor(int idDoc, String password, String name, LocalDate birthday, int telephone, String educationBackground, String specialty) {
        this.idDoc = idDoc;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.telephone = telephone;
        this.educationBackground = educationBackground;
        this.specialty = specialty;
    }
    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        if(birthday == null){
            birthday = LocalDate.now();
        }
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getReceptionRequirements() {
        return receptionRequirements;
    }

    public void setReceptionRequirements(String receptionRequirements) {
        this.receptionRequirements = receptionRequirements;
    }

    public String getAvailableTimings() {
        return availableTimings;
    }

    public void setAvailableTimings(String availableTimings) {
        this.availableTimings = availableTimings;
    }

}
