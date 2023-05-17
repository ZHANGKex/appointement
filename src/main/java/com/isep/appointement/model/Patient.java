package com.isep.appointement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private Roles role = Roles.Patient;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false)
    private int sex; // 0:female, 1:male, 2: other ...

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "cases")
    private String caseImg;

    @Column(name = "phone", nullable = false, length = 20)
    private int telephone;

    @Column(name = "email", nullable = false, length = 50)
    private String mail;

    @Column(name = "id_number", nullable = false, length = 20)
    private String idNumber;

    @Column(name = "job", length = 20)
    private String job;

    @Lob
    private String allergens;

    @Lob
    private String chronicDiseases;

    @Lob
    private String geneticDiseases;

    public Patient() {
    }

    public Patient(Long id, String username, String password, String name, int age, int sex, String address, String caseImg, int telephone, String mail, String idNumber, String job, String allergens, String chronicDiseases, String geneticDiseases) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.caseImg = caseImg;
        this.telephone = telephone;
        this.mail = mail;
        this.idNumber = idNumber;
        this.job = job;
        this.allergens = allergens;
        this.chronicDiseases = chronicDiseases;
        this.geneticDiseases = geneticDiseases;
    }

    //Attributes necessary
    public Patient(Long id, String username, String password, String name, int age, int sex, int telephone, String mail, String idNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telephone = telephone;
        this.mail = mail;
        this.idNumber = idNumber;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Roles getRoles() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
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

    public String getSex() {
        if(sex == 0){
            return "female";
        }
        else if(sex == 1){
            return "male";
        }
        return "other";
    }

    public void setSex(String sex) {
        if(sex == "female"){
            this.sex = 0;
        }
        else if(sex == "male"){
            this.sex = 1;
        }
        this.sex = 2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCaseImg() {
        return caseImg;
    }

    public void setCaseImg(String caseImg) {
        this.caseImg = caseImg;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getGeneticDiseases() {
        return geneticDiseases;
    }

    public void setGeneticDiseases(String geneticDiseases) {
        this.geneticDiseases = geneticDiseases;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", caseImg='" + caseImg + '\'' +
                ", telephone=" + telephone +
                ", mail='" + mail + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", job='" + job + '\'' +
                ", allergens='" + allergens + '\'' +
                ", chronicDiseases='" + chronicDiseases + '\'' +
                ", geneticDiseases='" + geneticDiseases + '\'' +
                '}';
    }
}
