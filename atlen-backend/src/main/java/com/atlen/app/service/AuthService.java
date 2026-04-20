package com.atlen.app.service;

import com.atlen.app.dto.AuthDto;
import com.atlen.app.entity.User;
import com.atlen.app.repository.UserRepository;
import com.atlen.app.security.CustomUserDetails;
import com.atlen.app.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthDto.AuthResponse authenticateUser(AuthDto.LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
        User user = userDetails.getUser();

        return new AuthDto.AuthResponse(jwt, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public AuthDto.AuthResponse registerUser(AuthDto.RegisterRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        // A simple trick to login right after register without duplicate login object
        AuthDto.LoginRequest loginReq = new AuthDto.LoginRequest();
        loginReq.setEmail(signUpRequest.getEmail());
        loginReq.setPassword(signUpRequest.getPassword());
        return authenticateUser(loginReq);
    }
}
