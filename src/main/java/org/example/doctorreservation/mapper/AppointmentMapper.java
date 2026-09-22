package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.AppointmentRequest;
import org.example.doctorreservation.dto.AppointmentResponse;
import org.example.doctorreservation.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "patientId",source = "patient.id")
    @Mapping(target = "doctorId",source = "doctor.id")
    @Mapping(target = "paymentId",source = "payment.id")
    AppointmentResponse toResponse(Appointment appointment);
    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "payment",ignore = true)
    Appointment toModel(AppointmentRequest appointmentRequest);
    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "payment",ignore = true)
    Appointment updateModel(AppointmentRequest appointmentRequest, @MappingTarget Appointment appointment);
}
