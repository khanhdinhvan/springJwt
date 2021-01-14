package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.SearchRequest;
import com.khanhdv.spring.jwt.payload.request.SignupRequest;
import com.khanhdv.spring.jwt.payload.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> create(SignupRequest signUpRequest);

    ResponseEntity<?> findAll();

    ResponseEntity<?> search(SearchRequest request);

    ResponseEntity<?> update(UserUpdateRequest request);

    ResponseEntity<?> find(Long userId);
}
