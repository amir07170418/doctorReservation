package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.DoctorRequest;
import org.example.doctorreservation.dto.DoctorResponse;
import org.example.doctorreservation.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "specialtyId",source = "specialty.id")
    DoctorResponse toResponse(Doctor doctor);
    @Mapping(target = "specialty",ignore = true)
    @Mapping(target = "password",ignore = true)
    Doctor toModel(DoctorRequest doctorRequest);
    @Mapping(target = "specialty",ignore = true)
    @Mapping(target = "password",ignore = true)
    Doctor updateModel(DoctorRequest request, @MappingTarget Doctor doctor);
}
