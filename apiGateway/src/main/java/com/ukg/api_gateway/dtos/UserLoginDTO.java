package com.ukg.api_gateway.dtos;

import com.ukg.api_gateway.helper.Role;
import lombok.Getter;

@Getter
public class UserLoginDTO {
    private String email;
    private String password;
    private Role role;
}
