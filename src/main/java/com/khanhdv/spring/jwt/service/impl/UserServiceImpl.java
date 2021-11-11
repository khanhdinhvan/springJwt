package com.khanhdv.spring.jwt.service.impl;

import com.khanhdv.spring.jwt.common.converter.ObjectMapperUtils;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.payload.request.SearchRequest;
import com.khanhdv.spring.jwt.payload.request.user.UserRegisterRequest;
import com.khanhdv.spring.jwt.payload.request.user.UserUpdateRequest;
import com.khanhdv.spring.jwt.payload.response.MessageResponse;
import com.khanhdv.spring.jwt.payload.response.RoleData;
import com.khanhdv.spring.jwt.payload.response.UserResponse;
import com.khanhdv.spring.jwt.repository.RoleRepository;
import com.khanhdv.spring.jwt.repository.UserRepository;
import com.khanhdv.spring.jwt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<?> create(UserRegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .isActive(request.isActive())
                .isDeleted(false)
                .build();
        if (!request.getRoleId().isEmpty()) {
            List<Role> userRole = roleRepository.findByIdIn(request.getRoleId());
            user.setRoles(userRole);
        }
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> result = users.stream()
                .map(x -> {
                    UserResponse userResponse = UserResponse.builder()
                            .id(x.getId())
                            .username(x.getUsername())
                            .email(x.getEmail())
                            .isActive(x.getIsActive())
                            .roles(x.getRoles().stream().map(role -> RoleData
                                            .builder()
                                            .name(role.getName()).id(role.getId()).build())
                                    .collect(Collectors.toList()))
                            .build();
                    return userResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);

    }

    @Override

    public ResponseEntity<?> search(SearchRequest request) {
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(userRepository.findByIdAndUsername(request.getId(), request.getUsername()), UserResponse.class));
    }

    @Override
    public ResponseEntity<?> update(UserUpdateRequest request) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(request.getId());
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("User update errors!");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (StringUtils.isNotBlank(request.getPassword())) {
            user.setPassword(encoder.encode(request.getPassword()));
        }
        user.setIsActive(request.isActive());
        List<Role> userRole = roleRepository.findByIdIn(request.getRoleId());
        user.setRoles(userRole);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User update successfully!"));
    }

    @Override
    public ResponseEntity<?> find(Long userId) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(userId);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException("User find errors!");
        }
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .roles(user.getRoles().stream().map(role -> RoleData
                                .builder()
                                .name(role.getName()).id(role.getId()).build())
                        .collect(Collectors.toList()))
                .build();
        return ResponseEntity.ok(userResponse);
    }
}
