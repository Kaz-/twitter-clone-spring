package com.kazlabs.twitterclone.api.service;

import com.kazlabs.twitterclone.api.dao.request.SignUpRequest;
import com.kazlabs.twitterclone.api.dao.request.SigninRequest;
import com.kazlabs.twitterclone.api.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
