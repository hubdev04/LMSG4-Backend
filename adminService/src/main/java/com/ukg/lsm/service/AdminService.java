package com.ukg.lsm.service;

import com.ukg.lsm.entity.AdminEntity;
import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.ResourceNotFoundException;

import java.util.List;

public interface AdminService {
    List<AdminEntity> getAdmins() throws ResourceNotFoundException;
    AdminEntity createAdmin(AdminEntity admin) throws InvalidRequest;

}
