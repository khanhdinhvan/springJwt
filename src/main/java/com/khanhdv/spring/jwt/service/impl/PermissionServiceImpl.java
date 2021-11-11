package com.khanhdv.spring.jwt.service.impl;

import com.khanhdv.spring.jwt.common.converter.ObjectMapperUtils;
import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.payload.request.permission.PermissionRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.permission.PermissionUpdateRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleUpdateRequest;
import com.khanhdv.spring.jwt.payload.response.MessageResponse;
import com.khanhdv.spring.jwt.payload.response.PermissionResponse;
import com.khanhdv.spring.jwt.payload.response.RoleResponse;
import com.khanhdv.spring.jwt.repository.PermissionRepository;
import com.khanhdv.spring.jwt.repository.RoleRepository;
import com.khanhdv.spring.jwt.service.PermissionService;
import com.khanhdv.spring.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public ResponseEntity<?> create(PermissionRegisterRequest request) {
        Permission permission = Permission.builder()
                .url(request.getUrl())
                .isDeleted(false)
                .build();
        permissionRepository.save(permission);
        return ResponseEntity.ok(new MessageResponse("Permission registered successfully!"));
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<Permission> roles = permissionRepository.findAll();
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(roles, PermissionResponse.class));
    }

    @Override
    public ResponseEntity<?> find(Long roleId) {
        Permission permission = permissionRepository.findByIdAndIsDeletedIsFalse(roleId);
        if (Objects.isNull(permission)) {
            throw new EntityNotFoundException("Permission find errors!");
        }
        return ResponseEntity.ok(ObjectMapperUtils.map(permission, PermissionResponse.class));
    }

    @Override
    public ResponseEntity<?> update(PermissionUpdateRequest request) {
        Permission permission = permissionRepository.findByIdAndIsDeletedIsFalse(request.getId());
        if (Objects.isNull(permission)) {
            throw new EntityNotFoundException("Permission update errors!");
        }
        permission.setUrl(request.getUrl());
        permissionRepository.save(permission);
        return ResponseEntity.ok(new MessageResponse("Permission update successfully!"));
    }
}