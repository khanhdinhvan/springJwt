package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.SearchRequest;
import com.khanhdv.spring.jwt.payload.request.user.UserRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.user.UserUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> create(UserRegisterRequest signUpRequest);

    ResponseEntity<?> findAll();

    ResponseEntity<?> search(SearchRequest request);

    ResponseEntity<?> update(UserUpdateRequest request);

    ResponseEntity<?> find(Long userId);
}
