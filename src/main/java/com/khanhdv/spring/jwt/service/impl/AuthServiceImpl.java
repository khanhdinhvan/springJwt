package com.khanhdv.spring.jwt.service.impl;

import com.khanhdv.spring.jwt.payload.request.LoginRequest;
import com.khanhdv.spring.jwt.payload.response.JwtResponse;
import com.khanhdv.spring.jwt.security.jwt.JwtUtils;
import com.khanhdv.spring.jwt.security.services.UserPrincipal;
import com.khanhdv.spring.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse auth(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
