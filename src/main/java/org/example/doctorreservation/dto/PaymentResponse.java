package org.example.doctorreservation.dto;

import org.example.doctorreservation.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private Long amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
    private Long appointmentId;

    public PaymentResponse(Long id, Long amount, LocalDateTime paymentDate, PaymentStatus status, Long appointmentId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.appointmentId = appointmentId;
    }

    public PaymentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                ", appointmentId=" + appointmentId +
                '}';
    }
}
