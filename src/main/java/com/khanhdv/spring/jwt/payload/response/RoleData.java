package com.khanhdv.spring.jwt.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleData {

    private String name;

    private Long id;
}
