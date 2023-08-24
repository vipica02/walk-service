package com.ttc.walk.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CreateUserRequest {
    @NotBlank(message = "Không được bỏ trống thông tin username")
    private String username;
    @NotBlank(message = "Không được bỏ trống thông tin họ tên")
    private String fullName;
    @NotBlank(message = "Không được bỏ trống thông tin sdt")
    private String phone;
    @NotBlank(message = "Không được bỏ trống thông tin email")
    private String email;
    private String password;
}
