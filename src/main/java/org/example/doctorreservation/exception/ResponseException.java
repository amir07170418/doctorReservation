package org.example.doctorreservation.exception;

import java.time.LocalDateTime;

public class ResponseException {
    private Integer status;
    private String message;
    private LocalDateTime time;

    public ResponseException(Integer status, String message, LocalDateTime time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public ResponseException() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ResponseException{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
