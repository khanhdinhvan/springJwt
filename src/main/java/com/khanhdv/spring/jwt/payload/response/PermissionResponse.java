package com.khanhdv.spring.jwt.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse {

    private Long id;

    private String url;

}
