package com.khanhdv.spring.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotEmpty(message="{user.name.notEmpty}")
    private String username;

    @NotEmpty(message = "{email.notEmpty}")
    @Email(message = "{email.invalidate}")
    private String email;

    @NotEmpty
    private List<String> role;

    @NotEmpty
    private String password;

}
