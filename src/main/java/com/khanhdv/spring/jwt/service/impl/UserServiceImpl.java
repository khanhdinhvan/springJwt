package com.khanhdv.spring.jwt.service.impl;

import com.khanhdv.spring.jwt.common.converter.ObjectMapperUtils;
import com.khanhdv.spring.jwt.common.enums.ERole;
import com.khanhdv.spring.jwt.dto.UserMapper;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.payload.request.SearchRequest;
import com.khanhdv.spring.jwt.payload.request.SignupRequest;
import com.khanhdv.spring.jwt.payload.request.UserUpdateRequest;
import com.khanhdv.spring.jwt.payload.response.MessageResponse;
import com.khanhdv.spring.jwt.payload.response.UserResponse;
import com.khanhdv.spring.jwt.repository.RoleRepository;
import com.khanhdv.spring.jwt.repository.UserRepository;
import com.khanhdv.spring.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> create(SignupRequest signUpRequest) {

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .isActive(true)
                .isDeleted(false)
                .build();

        List<String> strRoles = signUpRequest.getRole();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(UserMapper.INSTANCE.toListUserResponse(userRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> search(SearchRequest request) {
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(userRepository.findByIdAndUsername(request.getId(), request.getUsername()), UserResponse.class));
    }

    @Override
    public ResponseEntity<?> update(UserUpdateRequest request) {
        Optional<User> user = userRepository.findById(request.getId());
        if (user != null) {
            UserMapper.INSTANCE.toUser(request, user.get());
            userRepository.save(user.get());
            return ResponseEntity.ok(new MessageResponse("User update successfully!"));
        }
        return ResponseEntity.ok(new MessageResponse("User update errors!"));
    }

    @Override
    public ResponseEntity<?> find(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return ResponseEntity.ok(user != null ? UserMapper.INSTANCE.toUserResponse(user.get()) : null);
    }
}
