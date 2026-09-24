package org.example.doctorreservation.service;

import jakarta.transaction.Transactional;
import org.example.doctorreservation.dto.PatientRequest;
import org.example.doctorreservation.dto.PatientResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.PatientMapper;
import org.example.doctorreservation.model.Patient;
import org.example.doctorreservation.model.Role;
import org.example.doctorreservation.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public PatientResponse register(PatientRequest patientRequest) {
        Patient patient = patientMapper.toModel(patientRequest);
        patient.setRole(Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }
    @Transactional
    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("patient not found", HttpStatus.NOT_FOUND));
        patientMapper.updateModel(patientRequest, patient);
        patient.setRole(Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(patientRequest.getPassword()));
        patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }
    public PatientResponse findById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("patient not found", HttpStatus.NOT_FOUND));
        return patientMapper.toResponse(patient);
    }
    public Page<PatientResponse> findAll(Pageable pageable) {
        Page<Patient> patient = patientRepository.findAll(pageable);
        return patient.map(patientMapper::toResponse);
    }
    public void deleteById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->
                new   DoctorReservationException("patient not found", HttpStatus.NOT_FOUND));
        patientRepository.delete(patient);
    }
}
