package org.example.doctorreservation.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private String description;
    @NotNull
    private Long patientId;
    @NotNull
    private Long doctorId;

    public AppointmentRequest(LocalDate appointmentDate, LocalTime startTime,
                              String description, Long patientId, Long doctorId) {
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public AppointmentRequest() {
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "AppointmentRequest{" +
                "appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", description='" + description + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
