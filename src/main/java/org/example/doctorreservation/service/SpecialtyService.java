package org.example.doctorreservation.service;

import org.example.doctorreservation.dto.SpecialtyRequest;
import org.example.doctorreservation.dto.SpecialtyResponse;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.mapper.SpecialtyMapper;
import org.example.doctorreservation.model.Specialty;
import org.example.doctorreservation.repository.SpecialtyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;
    public SpecialtyService(SpecialtyRepository specialtyRepository, SpecialtyMapper specialtyMapper) {
        this.specialtyRepository = specialtyRepository;
        this.specialtyMapper = specialtyMapper;
    }
    public SpecialtyResponse save(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyMapper.toModel(specialtyRequest);
        specialtyRepository.save(specialty);
        return specialtyMapper.toResponse(specialty);
    }
    public SpecialtyResponse findById(Long id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(()->
                new DoctorReservationException("specialty not found", HttpStatus.NOT_FOUND));
        return specialtyMapper.toResponse(specialty);
    }
    @Transactional
    public SpecialtyResponse updateSpecialty(Long id, SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("specialty not found", HttpStatus.NOT_FOUND));
        specialtyMapper.updateModel(specialtyRequest,specialty);
        specialtyRepository.save(specialty);
        return specialtyMapper.toResponse(specialty);
    }
    public Page<SpecialtyResponse> findAll(Pageable pageable) {
        Page<Specialty> specialtyPage = specialtyRepository.findAll(pageable);
        return specialtyPage.map(specialtyMapper::toResponse);
    }
    public void  deleteById(Long id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(()->
                new  DoctorReservationException("specialty not found", HttpStatus.NOT_FOUND));
        specialtyRepository.delete(specialty);
    }
}
