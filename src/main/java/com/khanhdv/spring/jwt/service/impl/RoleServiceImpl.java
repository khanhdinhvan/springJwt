package com.khanhdv.spring.jwt.service.impl;

import com.khanhdv.spring.jwt.common.converter.ObjectMapperUtils;
import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.payload.request.role.RoleRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.role.RoleUpdateRequest;
import com.khanhdv.spring.jwt.payload.response.*;
import com.khanhdv.spring.jwt.repository.PermissionRepository;
import com.khanhdv.spring.jwt.repository.RoleRepository;
import com.khanhdv.spring.jwt.repository.UserRepository;
import com.khanhdv.spring.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private PermissionRepository permissionRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public ResponseEntity<?> create(RoleRegisterRequest request) {
        Role role = Role.builder()
                .name(request.getName())
                .isDeleted(false)
                .build();
        if (!request.getPermissions().isEmpty()) {
            List<Permission> permission = permissionRepository.findByIdIn(request.getPermissions());
            role.setPermissions(permission);
        }
        roleRepository.save(role);
        return ResponseEntity.ok(new MessageResponse("Role registered successfully!"));
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> result = roles.stream()
                .map(x -> {
                    RoleResponse roleResponse = RoleResponse.builder()
                            .id(x.getId())
                            .name(x.getName())
                            .permissions(x.getPermissions().stream().map(per -> PermissionResponse
                                            .builder()
                                            .url(per.getUrl()).id(per.getId()).build())
                                    .collect(Collectors.toList()))
                            .build();
                    return roleResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<?> find(Long roleId) {
        Role role = roleRepository.findByIdAndIsDeletedIsFalse(roleId);
        if (Objects.isNull(role)) {
            throw new EntityNotFoundException("Role find errors!");
        }
        RoleResponse userResponse = RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions().stream().map(permission -> PermissionResponse
                                .builder()
                                .url(permission.getUrl()).id(permission.getId()).build())
                        .collect(Collectors.toList()))
                .build();
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<?> update(RoleUpdateRequest request) {
        Role role = roleRepository.findByIdAndIsDeletedIsFalse(request.getId());
        if (Objects.isNull(role)) {
            throw new EntityNotFoundException("Role update errors!");
        }
        role.setName(request.getName());
        if (!request.getPermissions().isEmpty()) {
            List<Permission> permission = permissionRepository.findByIdIn(request.getPermissions());
            role.setPermissions(permission);
        }
        roleRepository.save(role);
        return ResponseEntity.ok(new MessageResponse("Role update successfully!"));
    }
}
