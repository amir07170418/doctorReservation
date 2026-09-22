package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.MedicalRecordRequest;
import org.example.doctorreservation.dto.MedicalRecordResponse;
import org.example.doctorreservation.model.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    @Mapping(target = "patientId",source = "patient.id")
    @Mapping(target = "doctorId",source = "patient.id")
    MedicalRecordResponse toResponse(MedicalRecord medicalRecord);
    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    MedicalRecord toModel(MedicalRecordRequest medicalRecordRequest);
    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    MedicalRecord updateModel(MedicalRecordRequest request, @MappingTarget MedicalRecord medicalRecord);
}
