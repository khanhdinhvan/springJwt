package com.khanhdv.spring.jwt.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequest {

    private Long id;

//    @NotEmpty(message="{user.name.notEmpty}")
//    private String username;

    @NotEmpty(message = "{email.notEmpty}")
    @Email(message = "{email.invalidate}")
    private String email;

}
