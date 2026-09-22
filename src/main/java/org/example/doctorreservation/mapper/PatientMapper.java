package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.PatientRequest;
import org.example.doctorreservation.dto.PatientResponse;
import org.example.doctorreservation.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientResponse toResponse(Patient patient);
    @Mapping(target = "password",ignore = true)
    Patient toModel(PatientRequest patientRequest);
    @Mapping(target = "password",ignore = true)
    Patient updateModel(PatientRequest patientRequest, @MappingTarget Patient patient);
}
