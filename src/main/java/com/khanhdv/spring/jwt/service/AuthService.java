package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.LoginRequest;
import com.khanhdv.spring.jwt.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse auth(LoginRequest loginRequest);
}
