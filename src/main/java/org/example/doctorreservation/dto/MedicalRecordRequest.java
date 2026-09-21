package org.example.doctorreservation.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicalRecordRequest {
    @NotBlank
    private String diagnosis;
    @NotBlank
    private String description;
    @NotNull
    private Long patientId;
    @NotNull
    private Long doctorId;

    public MedicalRecordRequest(String diagnosis, String description, Long patientId, Long doctorId) {
        this.diagnosis = diagnosis;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public MedicalRecordRequest() {
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
        return "MedicalRecordRequest{" +
                "diagnosis='" + diagnosis + '\'' +
                ", description='" + description + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
