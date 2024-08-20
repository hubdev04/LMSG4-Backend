package com.ukg.authService.dtos;

import com.ukg.authService.helper.Role;
import lombok.Getter;

@Getter
public class UserLoginDTO {
    private String email;
    private String password;
    private Role role;
}
