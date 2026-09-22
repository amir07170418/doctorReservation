package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.SpecialtyRequest;
import org.example.doctorreservation.dto.SpecialtyResponse;
import org.example.doctorreservation.model.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyResponse toResponse(Specialty specialty);
    Specialty toModel(SpecialtyRequest specialtyRequest);
    Specialty updateModel(SpecialtyRequest request, @MappingTarget Specialty specialty);
}
