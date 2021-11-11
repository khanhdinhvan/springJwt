package com.khanhdv.spring.jwt.payload.request.user;

import com.google.gson.annotations.SerializedName;
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
public class UserRegisterRequest {

    @SerializedName("UserName")
    @NotEmpty(message = "{error.message.common.notEmpty} userName. ")
    private String username;

    @SerializedName("Email")
    @NotEmpty(message = "{error.message.common.notEmpty} email.")
    @Email(message = "{error.user.email.invalidate}", regexp = "^(.+)@(.+)$")
    private String email;

    private List<Long> roleId;

    private String password;

    private boolean active;

}
