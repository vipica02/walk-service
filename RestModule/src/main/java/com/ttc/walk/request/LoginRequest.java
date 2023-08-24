package com.ttc.walk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class LoginRequest {
    @NotBlank(message = "Không được bỏ trống thông tin username")
    private String username;
    @NotBlank(message = "Không được bỏ trống thông tin password")
    private String password;
}
