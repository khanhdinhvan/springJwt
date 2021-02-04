package com.khanhdv.spring.jwt.payload.request;

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
public class SignupRequest {

    @NotEmpty(message = "{error.user.name.notEmpty}")
    private String username;

    @NotEmpty(message = "{error.user.email.notEmpty}")
    @Email(message = "{error.user.email.invalidate}")
    private String email;

    private List<String> role;

    private String password;

}
