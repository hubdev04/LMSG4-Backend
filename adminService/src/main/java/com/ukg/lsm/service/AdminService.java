package com.ukg.lsm.service;

import com.ukg.lsm.entity.AdminEntity;
import com.ukg.lsm.utils.InvalidRequestException;

import java.util.List;

public interface AdminService {
    List<AdminEntity> getAdmins();
    AdminEntity createAdmin(AdminEntity admin) throws InvalidRequestException;

}
