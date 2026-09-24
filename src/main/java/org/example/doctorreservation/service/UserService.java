package org.example.doctorreservation.service;

import org.example.doctorreservation.dto.UserRequest;
import org.example.doctorreservation.exception.DoctorReservationException;
import org.example.doctorreservation.model.User;
import org.example.doctorreservation.repository.UserRepository;
import org.example.doctorreservation.security.JwtResponse;
import org.example.doctorreservation.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public ResponseEntity<JwtResponse> login(UserRequest userRequest){
        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow(()->
                new DoctorReservationException("Username or password incorrect", HttpStatus.UNAUTHORIZED));
        if (!passwordEncoder.matches(userRequest.getPassword(),user.getPassword())){
            throw new DoctorReservationException("Username or password incorrect", HttpStatus.UNAUTHORIZED);
        }
        JwtResponse jwtResponse = new JwtResponse(jwtService.generateToken(user.getUsername()),user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
