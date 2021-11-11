package com.khanhdv.spring.jwt.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private boolean isActive;

    private List<RoleData> roles;

}
