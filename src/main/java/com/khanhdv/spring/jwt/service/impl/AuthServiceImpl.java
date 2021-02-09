package com.khanhdv.spring.jwt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khanhdv.spring.jwt.payload.request.LoginRequest;
import com.khanhdv.spring.jwt.payload.response.UserInfo;
import com.khanhdv.spring.jwt.security.jwt.JwtUtils;
import com.khanhdv.spring.jwt.security.services.UserPrincipal;
import com.khanhdv.spring.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
    public String auth(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        Map<String, String> token = new HashMap();
        token.put("token", jwtUtils.generateToken(userDetails));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(UserPrincipal user) {
        return new UserInfo(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList()));
    }

}
