package org.example.doctorreservation.service;

import jakarta.transaction.Transactional;
import org.example.doctorreservation.dto.MedicalRecordRequest;
import org.example.doctorreservation.dto.MedicalRecordResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.MedicalRecordMapper;
import org.example.doctorreservation.model.Doctor;
import org.example.doctorreservation.model.MedicalRecord;
import org.example.doctorreservation.model.Patient;
import org.example.doctorreservation.repository.DoctorRepository;
import org.example.doctorreservation.repository.MedicalRecordRepository;
import org.example.doctorreservation.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    @Transactional
    public MedicalRecordResponse saveMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        MedicalRecord  medicalRecord = medicalRecordMapper.toModel(medicalRecordRequest);
        Patient patient = patientRepository.findById(medicalRecordRequest.getPatientId()).orElseThrow(()->
                new DoctorReservationException("patient not found", HttpStatus.NOT_FOUND));
        Doctor doctor = doctorRepository.findById(medicalRecordRequest.getDoctorId()).orElseThrow(()->
                new  DoctorReservationException("doctor not found", HttpStatus.NOT_FOUND));
        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);
        medicalRecord.setCreatedAt(LocalDateTime.now());
        medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toResponse(medicalRecord);
    }
    @Transactional
    public MedicalRecordResponse updateMedicalRecord(Long id,MedicalRecordRequest medicalRecordRequest) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("medical record not found", HttpStatus.NOT_FOUND));
        medicalRecordMapper.updateModel(medicalRecordRequest, medicalRecord);
        Patient patient = patientRepository.findById(medicalRecordRequest.getPatientId()).orElseThrow(()->
                new DoctorReservationException("patient not found", HttpStatus.NOT_FOUND));
        Doctor doctor = doctorRepository.findById(medicalRecordRequest.getDoctorId()).orElseThrow(()->
                new  DoctorReservationException("doctor not found", HttpStatus.NOT_FOUND));
        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);
        medicalRecordRepository.save(medicalRecord);
        return  medicalRecordMapper.toResponse(medicalRecord);
    }
    public MedicalRecordResponse findById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("medical record not found", HttpStatus.NOT_FOUND));
        return medicalRecordMapper.toResponse(medicalRecord);
    }
    public Page<MedicalRecordResponse> findAll(Pageable pageable) {
        Page<MedicalRecord> medicalRecordPage = medicalRecordRepository.findAll(pageable);
        return medicalRecordPage.map(medicalRecordMapper::toResponse);
    }
    public void deleteById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("medical record not found", HttpStatus.NOT_FOUND));
        medicalRecordRepository.delete(medicalRecord);
    }
}
