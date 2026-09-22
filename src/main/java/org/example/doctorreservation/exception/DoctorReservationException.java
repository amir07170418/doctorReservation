package org.example.doctorreservation.exception;

import org.springframework.http.HttpStatus;

public class DoctorReservationException extends  RuntimeException {
    private HttpStatus status;

    public DoctorReservationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
