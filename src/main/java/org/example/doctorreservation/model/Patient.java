package org.example.doctorreservation.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Patient extends  User {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phone;
    private Integer age;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "patient")
    private Set<Appointment> appointments;
    @OneToMany(fetch =  FetchType.LAZY,mappedBy = "patient")
    private Set<MedicalRecord> medicalRecords;

    public Patient(Long id, String username, String password, String email, Role role, String firstName, String lastName
            , String phone, Integer age, Set<Appointment> appointments, Set<MedicalRecord> medicalRecords) {
        super(id, username, password, email, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.appointments = appointments;
        this.medicalRecords = medicalRecords;
    }

    public Patient(String firstName, String lastName, String phone, Integer age, Set<Appointment> appointments, Set<MedicalRecord> medicalRecords) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.appointments = appointments;
        this.medicalRecords = medicalRecords;
    }

    public Patient() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
