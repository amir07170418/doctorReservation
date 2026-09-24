package org.example.doctorreservation.service;

import jakarta.transaction.Transactional;
import org.example.doctorreservation.dto.DoctorRequest;
import org.example.doctorreservation.dto.DoctorResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.DoctorMapper;
import org.example.doctorreservation.model.Doctor;
import org.example.doctorreservation.model.Role;
import org.example.doctorreservation.model.Specialty;
import org.example.doctorreservation.repository.DoctorRepository;
import org.example.doctorreservation.repository.SpecialtyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SpecialtyRepository specialtyRepository;
    private final PasswordEncoder passwordEncoder;
    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper, SpecialtyRepository specialtyRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.specialtyRepository = specialtyRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public DoctorResponse register(DoctorRequest doctorRequest){
        Doctor doctor = doctorMapper.toModel(doctorRequest);
        Specialty specialty = specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElseThrow(()->new
                DoctorReservationException("specialty not found",HttpStatus.NOT_FOUND));
        doctor.setSpecialty(specialty);
        doctor.setRole(Role.DOCTOR);
        doctor.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
        doctorRepository.save(doctor);
        return doctorMapper.toResponse(doctor);
    }
    public DoctorResponse findById(Long id){
        Doctor doctor=doctorRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("doctor not found",HttpStatus.NOT_FOUND));
        return doctorMapper.toResponse(doctor);
    }
    @Transactional
    public DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("doctor not found",HttpStatus.NOT_FOUND));
        doctorMapper.updateModel(doctorRequest,doctor);
        doctor.setRole(Role.DOCTOR);
        doctor.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
        Specialty specialty = specialtyRepository.findById(doctorRequest.getSpecialtyId()).orElseThrow(()->new
                DoctorReservationException("specialty not found",HttpStatus.NOT_FOUND));
        doctor.setSpecialty(specialty);
        doctorRepository.save(doctor);
        return doctorMapper.toResponse(doctor);
    }
    public Page<DoctorResponse> findAll(Pageable pageable){
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
        return doctorPage.map(doctorMapper::toResponse);
    }
    public void deleteById(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("doctor not found",HttpStatus.NOT_FOUND));
        doctorRepository.delete(doctor);
    }
}
