package com.khanhdv.spring.jwt.payload.request.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequest {

    private Long id;

    @NotEmpty(message="{user.name.notEmpty}")
    private String username;

    @NotEmpty(message = "{email.notEmpty}")
    @Email(message = "{email.invalidate}")
    private String email;

    private List<Long> roleId;

    private boolean active;

    private String password;
}
