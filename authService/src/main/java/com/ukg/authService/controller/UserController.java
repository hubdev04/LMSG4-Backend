package com.ukg.authService.controller;

import com.ukg.authService.dtos.UserLoginDTO;
import com.ukg.authService.entity.UserEntity;
import com.ukg.authService.exceptions.InvalidRequest;
import com.ukg.authService.exceptions.NoAuthorisationException;
import com.ukg.authService.helper.JWTUtil;
import com.ukg.authService.service.UserService;
import com.ukg.authService.configuration.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseDTO registerUser(@RequestBody UserEntity user) throws InvalidRequest {
        System.out.println("\n\n------------------------\n\n");
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("User registered successfully")
                .result(userService.registerUser(user))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/login")
    public ResponseDTO loginUser(@RequestBody UserLoginDTO user) throws NoAuthorisationException {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("User logged in successfully")
                .result(userService.loginUser(user))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/validateToken")
    public void validateToken(){
    }
}
