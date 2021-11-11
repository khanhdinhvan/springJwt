package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.permission.PermissionRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.permission.PermissionUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface PermissionService {

    ResponseEntity<?> create(PermissionRegisterRequest request);

    ResponseEntity<?> findAll();

    ResponseEntity<?> find(Long roleId);

    ResponseEntity<?> update(PermissionUpdateRequest request);

}
