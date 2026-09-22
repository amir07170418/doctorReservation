package org.example.doctorreservation.dto;


import java.time.LocalDateTime;

public class MedicalRecordResponse {
    private Long id;
    private String diagnosis;
    private String description;
    private LocalDateTime createdAt;
    private Long patientId;
    private Long doctorId;

    public MedicalRecordResponse(Long id, String diagnosis, String description
            , LocalDateTime createdAt, Long patientID, Long doctorId) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.description = description;
        this.createdAt = createdAt;
        this.patientId = patientID;
        this.doctorId = doctorId;
    }
    public MedicalRecordResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return "MedicalRecordResponse{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", patientID=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
