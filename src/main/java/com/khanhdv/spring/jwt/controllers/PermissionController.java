package com.khanhdv.spring.jwt.controllers;

import com.khanhdv.spring.jwt.payload.request.permission.PermissionRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.permission.PermissionUpdateRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleUpdateRequest;
import com.khanhdv.spring.jwt.service.PermissionService;
import com.khanhdv.spring.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PermissionController extends BaseController {

    private PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping(value = "${endpoint.permission.register}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createPermission(@Valid @RequestBody PermissionRegisterRequest request) {
        return permissionService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "${endpoint.permission.update}")
    public ResponseEntity<?> updatePermission(@Valid @RequestBody PermissionUpdateRequest request) {
        return permissionService.update(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.permission.find}")
    public ResponseEntity<?> findPermission(@PathVariable("permissionId") Long permissionId) {
        return permissionService.find(permissionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.permission.findAll}")
    public ResponseEntity<?> findPermission() {
        return permissionService.findAll();
    }

}

