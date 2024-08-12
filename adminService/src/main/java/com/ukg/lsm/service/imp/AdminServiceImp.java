package com.ukg.lsm.service.imp;

import com.ukg.lsm.entity.AdminEntity;
import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.repository.AdminRepository;
import com.ukg.lsm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    @Transactional
    public AdminEntity createAdmin(AdminEntity admin) throws InvalidRequest {

        if(adminRepo.existsByEmail(admin.getEmail())){
            throw new InvalidRequest("Email already exists");
        }

        return adminRepo.save(admin);
    }

    @Override
    public List<AdminEntity> getAdmins() throws ResourceNotFoundException {
        List<AdminEntity> response = adminRepo.findAll();

        if(response.isEmpty()){
            throw new ResourceNotFoundException("No admins found");
        }

        return adminRepo.findAll();
    }
}
