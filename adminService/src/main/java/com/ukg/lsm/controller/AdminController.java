package com.ukg.lsm.controller;

import com.ukg.lsm.entity.AdminEntity;
import com.ukg.lsm.service.AdminService;
import com.ukg.lsm.utils.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    private ResponseEntity<List<AdminEntity>> getAdmins(){
//        List<AdminEntity> adminList = adminService.getAdmins();
//        return new ResponseEntity<>(adminList, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdmins());
    }
    @PostMapping
    private ResponseEntity<AdminEntity> postAdmin(@RequestBody AdminEntity admin) throws InvalidRequestException {
//        AdminEntity adminRes = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }
}
