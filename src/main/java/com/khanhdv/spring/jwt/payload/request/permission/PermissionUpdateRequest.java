package com.khanhdv.spring.jwt.payload.request.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionUpdateRequest {

    private Long id;

    private String url;

}
