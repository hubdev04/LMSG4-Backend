package com.ukg.lsm.service.imp;

import com.ukg.lsm.entity.AdminEntity;
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
    public AdminEntity createAdmin(AdminEntity admin) throws InvalidRequestException {
//        if(adminRepo.existsByEmail(admin.getEmail())){
//            throw new InvalidRequestException("Email already exists");
//        }
        try{
            return adminRepo.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidRequestException("Email already exists");
        }

    }

    @Override
    public List<AdminEntity> getAdmins() {
        return adminRepo.findAll();
    }
}
