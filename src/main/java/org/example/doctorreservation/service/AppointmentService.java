package org.example.doctorreservation.service;

import jakarta.transaction.Transactional;
import org.example.doctorreservation.dto.AppointmentRequest;
import org.example.doctorreservation.dto.AppointmentResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.AppointmentMapper;
import org.example.doctorreservation.model.*;
import org.example.doctorreservation.repository.AppointmentRepository;
import org.example.doctorreservation.repository.DoctorRepository;
import org.example.doctorreservation.repository.PatientRepository;
import org.example.doctorreservation.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PaymentRepository paymentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper,
                              DoctorRepository doctorRepository, PatientRepository patientRepository, PaymentRepository paymentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public AppointmentResponse save(AppointmentRequest appointmentRequest) {
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(() ->
                new DoctorReservationException("Doctor not found", HttpStatus.NOT_FOUND));
        Patient patient=getPatient();
        LocalTime endTime = appointmentRequest.getStartTime().plusMinutes(15);
        if (appointmentRepository.existsByTime(appointmentRequest.getDoctorId(), appointmentRequest.getAppointmentDate()
                , appointmentRequest.getStartTime(), endTime))
            throw new DoctorReservationException("appointment in this time is exist", HttpStatus.BAD_REQUEST);
        Appointment appointment = appointmentMapper.toModel(appointmentRequest);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setEndTime(endTime);
        appointment.setStatus(AppointmentStatus.PENDING);
        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setAmount(300000L);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);
        appointment.setPayment(payment);

        appointmentRepository.save(appointment);
        paymentRepository.save(payment);
        return appointmentMapper.toResponse(appointment);
    }

    public AppointmentResponse findById(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new DoctorReservationException("Appointment not found", HttpStatus.NOT_FOUND));
        return appointmentMapper.toResponse(appointment);
    }

    public Page<AppointmentResponse> findAll(Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findAll(pageable);
        return appointments.map(appointmentMapper::toResponse);
    }

    @Transactional
    public AppointmentResponse cancelAppointment(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new
                DoctorReservationException("Appointment not found", HttpStatus.NOT_FOUND));
        checkPatient(appointment);
        if (AppointmentStatus.PENDING.equals(appointment.getStatus())) {
            appointment.setStatus(AppointmentStatus.CANCELLED);
            return appointmentMapper.toResponse(appointment);
        }
        throw new DoctorReservationException("Appointment is " + appointment.getStatus().toString(), HttpStatus.BAD_REQUEST);
    }

    private void checkPatient(Appointment appointment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals(appointment.getPatient().getUsername())) {
            throw new DoctorReservationException("you cant cancel this Appointment", HttpStatus.FORBIDDEN);
        }
    }
    private Patient getPatient(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient=patientRepository.findByUsername(username).orElseThrow(()->new  DoctorReservationException("user not found", HttpStatus.NOT_FOUND));
        return patient;
    }
}
