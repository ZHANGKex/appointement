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
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Patient implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Roles role = Roles.Patient;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false)
    private String sex;

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

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @Lob
    private String allergens;

    @Lob
    private String chronicDiseases;

    @Lob
    private String geneticDiseases;

    private Boolean locked = true;
    private Boolean enabled = false;

    public Patient() {
    }

    public Patient(Long id, String password, String name, int age, String sex, String address, String caseImg, int telephone, String mail, String idNumber, String job, String allergens, String chronicDiseases, String geneticDiseases) {
        this.id = id;
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
    public Patient(Long id, String password, String name, int age, String sex, int telephone, String mail, String idNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telephone = telephone;
        this.mail = mail;
        this.idNumber = idNumber;
        this.enabled = false;
        this.locked = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

/*    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Roles.Patient.name());
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
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
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

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
