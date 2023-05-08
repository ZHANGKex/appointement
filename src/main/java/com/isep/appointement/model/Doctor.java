package com.isep.appointement.model;

import javax.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int idDoc;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex", nullable = false)
    private int sex; // 0:female, 1:male, 2: other ...

    @Column(name = "phone", nullable = false, length = 20)
    private int telephone;

    @Column(name = "email", length = 50)
    private String mail;

    @Column(name = "education_background", nullable = false, length = 50)
    private String educationBackground;

    @Column(name = "specialty",nullable = false, length = 255)
    private String specialty;

    @Column(name = "title", length = 255)
    private String title;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id")
    private Departement departement;*/

    @Column(name = "resume", length = 255)
    private String resume;

    @Column(name = "reception_requirements", length = 255)
    private String receptionRequirements;

    @Column(name = "available_timings", length = 255)
    private String availableTimings;

    public Doctor() {

    }

    public Doctor(String name, int age, String educationBackground, String specialty, String title,
                  /*Departement departement, */String resume, String receptionRequirements, String availableTimings) {
        super();
        this.name = name;
        this.age = age;
        this.educationBackground = educationBackground;
        this.specialty = specialty;
        this.title = title;
        /*this.departement = departement;*/
        this.resume = resume;
        this.receptionRequirements = receptionRequirements;
        this.availableTimings = availableTimings;
    }

//All attributes

    public Doctor(int idDoc, String username, String password, String name, int age, int sex, int telephone, String mail, String educationBackground, String specialty, String title, String resume, String receptionRequirements, String availableTimings) {
        this.idDoc = idDoc;
        this.username = username;
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

    public Doctor(int idDoc, String username, String password, String name, int telephone, String educationBackground, String specialty) {
        this.idDoc = idDoc;
        this.username = username;
        this.password = password;
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    /*public Departement getDepartement() {
        return departement;
    }*/

    /*public void setDepartement(Departement departement) {
        this.departement = departement;
    }*/

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

    @Override
    public String toString() {
        return "Doctor{" +
                "idDoc=" + idDoc +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", telephone=" + telephone +
                ", mail='" + mail + '\'' +
                ", educationBackground='" + educationBackground + '\'' +
                ", specialty='" + specialty + '\'' +
                ", title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", receptionRequirements='" + receptionRequirements + '\'' +
                ", availableTimings='" + availableTimings + '\'' +
                '}';
    }
}
