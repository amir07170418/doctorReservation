package org.example.doctorreservation.service;

import jakarta.transaction.Transactional;
import org.example.doctorreservation.dto.PaymentResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.PaymentMapper;
import org.example.doctorreservation.model.Payment;
import org.example.doctorreservation.model.PaymentStatus;
import org.example.doctorreservation.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }
    public PaymentResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("payment not found",HttpStatus.NOT_FOUND));
        return paymentMapper.toResponse(payment);
    }
    @Transactional
    public PaymentResponse payReservation(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->
                new DoctorReservationException("payment not found",HttpStatus.NOT_FOUND));
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
        return paymentMapper.toResponse(payment);
    }
    @Transactional
    public PaymentResponse cancelPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->
                new DoctorReservationException("payment not found",HttpStatus.NOT_FOUND));
        payment.setStatus(PaymentStatus.CANCELLED);
        paymentRepository.save(payment);
        return paymentMapper.toResponse(payment);
    }
    public Page<PaymentResponse> findAll(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);
        return payments.map(paymentMapper::toResponse);
    }

}
