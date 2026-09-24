package org.example.doctorreservation.mapper;

import org.example.doctorreservation.dto.PaymentResponse;
import org.example.doctorreservation.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "appointmentId",source = "appointment.id")
    PaymentResponse toResponse(Payment payment);
}
