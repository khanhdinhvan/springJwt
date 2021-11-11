package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.role.RoleRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<?> create(RoleRegisterRequest request);

    ResponseEntity<?> findAll();

    ResponseEntity<?> find(Long roleId);

//    ResponseEntity<?> search(SearchRequest request);

    ResponseEntity<?> update(RoleUpdateRequest request);

}
