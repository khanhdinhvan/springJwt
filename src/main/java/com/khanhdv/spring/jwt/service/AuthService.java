package com.khanhdv.spring.jwt.service;

import com.khanhdv.spring.jwt.payload.request.LoginRequest;
import com.khanhdv.spring.jwt.payload.response.UserInfo;
import com.khanhdv.spring.jwt.security.services.UserPrincipal;

public interface AuthService {

    String auth(LoginRequest loginRequest);

    UserInfo getUserInfo(UserPrincipal userPrincipal);
}
