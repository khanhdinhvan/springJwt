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
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private Boolean isActive;

    private Boolean isDeleted;

    private List<Role> roles;

}
