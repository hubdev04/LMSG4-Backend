package com.ukg.lsm.controller;

import com.ukg.lsm.entity.AdminEntity;
import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.service.AdminService;
import com.ukg.lsm.utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    private ResponseEntity<ResponseDTO> getAdmins() throws ResourceNotFoundException {
        List<AdminEntity> adminList = adminService.getAdmins();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(true)
                .message("Admin successfully fetched")
                .completionTimeStamp(LocalDateTime.now())
                .results(adminList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping
    private ResponseEntity<ResponseDTO> postAdmin(@RequestBody AdminEntity admin) throws InvalidRequest {
        AdminEntity adminRes = adminService.createAdmin(admin);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(true)
                .message("Admin successfully created")
                .completionTimeStamp(LocalDateTime.now())
                .results(adminRes)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
