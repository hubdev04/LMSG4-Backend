package com.ukg.lsm.service;

import com.ukg.lsm.entity.AdminEntity;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface AdminService {
    List<AdminEntity> getAdmins();
    AdminEntity createAdmin(AdminEntity admin);

}
