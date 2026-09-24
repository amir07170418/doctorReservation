package org.example.doctorreservation.repository;

import org.example.doctorreservation.model.Appointment;
import org.example.doctorreservation.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select count(a)>0 from Appointment a where a.doctor.id=:doctorId and " +
            "a.appointmentDate=:appointmentDate and a.startTime<:endtime and a.endTime>:startTime and" +
            " a.status!=org.example.doctorreservation.model.AppointmentStatus.CANCELLED")
    boolean existsByTime(@Param("doctorId")Long doctorId, @Param("appointmentDate")LocalDate date, @Param("startTime")
    LocalTime startTime, @Param("endTime")LocalTime endTime);
}
