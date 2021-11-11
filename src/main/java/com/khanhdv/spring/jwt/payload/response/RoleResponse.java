package com.khanhdv.spring.jwt.payload.response;

import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {

    private Long id;

    private String name;

    private List<PermissionResponse> permissions;

}
