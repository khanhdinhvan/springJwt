package com.khanhdv.spring.jwt.controllers;

import com.khanhdv.spring.jwt.payload.request.role.RoleRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleUpdateRequest;
import com.khanhdv.spring.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoleController extends BaseController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "${endpoint.role.register}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleRegisterRequest request) {
        return roleService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "${endpoint.role.update}")
    public ResponseEntity<?> updateRole(@Valid @RequestBody RoleUpdateRequest request) {
        return roleService.update(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.role.find}")
    public ResponseEntity<?> findRole(@PathVariable("roleId") Long roleId) {
        return roleService.find(roleId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.role.findAll}")
    public ResponseEntity<?> findUser() {
        return roleService.findAll();
    }

}

