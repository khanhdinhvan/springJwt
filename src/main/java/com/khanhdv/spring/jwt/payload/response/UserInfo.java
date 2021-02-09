package com.khanhdv.spring.jwt.payload.response;

import com.khanhdv.spring.jwt.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String username;
    private String email;
    private List<String> roles;

}
