package org.example.doctorreservation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DoctorReservationException.class)
    public ResponseEntity<ResponseException> handleDoctorReservationException(DoctorReservationException ex)
    {
        ResponseException responseException = new ResponseException(ex.getStatus().value(), ex.getMessage()
                , LocalDateTime.now());
        return ResponseEntity.status(responseException.getStatus()).body(responseException);
    }
}
