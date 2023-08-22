package com.kazlabs.twitterclone.api.service.impl;

import com.kazlabs.twitterclone.api.dao.request.SignUpRequest;
import com.kazlabs.twitterclone.api.dao.request.SigninRequest;
import com.kazlabs.twitterclone.api.dao.response.JwtAuthenticationResponse;
import com.kazlabs.twitterclone.api.entity.User;
import com.kazlabs.twitterclone.api.repository.UserRepository;
import com.kazlabs.twitterclone.api.service.AuthenticationService;
import com.kazlabs.twitterclone.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().username(request.getUsername()).name(request.getName()).bio(request.getBio())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
