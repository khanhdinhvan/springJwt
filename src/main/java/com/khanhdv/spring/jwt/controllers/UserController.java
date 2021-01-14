package com.khanhdv.spring.jwt.controllers;

import com.khanhdv.spring.jwt.payload.request.SearchRequest;
import com.khanhdv.spring.jwt.payload.request.SignupRequest;
import com.khanhdv.spring.jwt.payload.request.UserUpdateRequest;
import com.khanhdv.spring.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "${endpoint.user.create}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.create(signUpRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "${endpoint.user.update}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return userService.update(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.user.find}")
    public ResponseEntity<?> findUser(@PathVariable("userId") Long userId) {
        return userService.find(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "${endpoint.user.findAll}")
    public ResponseEntity<?> findAllUser() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestBody SearchRequest request) {
        return userService.search(request);
    }


}

