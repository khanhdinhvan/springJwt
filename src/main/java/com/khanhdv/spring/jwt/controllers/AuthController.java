package com.khanhdv.spring.jwt.controllers;

import com.khanhdv.spring.jwt.common.annotion.CurrentUser;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.payload.request.LoginRequest;
import com.khanhdv.spring.jwt.security.services.UserPrincipal;
import com.khanhdv.spring.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController extends BaseController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "${endpoint.auth.signIn}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.auth(loginRequest));
    }

    @GetMapping(value = "${endpoint.user.userInfo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserInfo(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(authService.getUserInfo(userPrincipal));
    }

}
