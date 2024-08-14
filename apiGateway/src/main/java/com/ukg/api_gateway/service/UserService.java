package com.ukg.api_gateway.service;

import com.ukg.api_gateway.entity.UserEntity;
import com.ukg.api_gateway.exceptions.InvalidRequest;
import com.ukg.api_gateway.exceptions.NoAuthorisationException;
import com.ukg.api_gateway.helper.Role;
import com.ukg.api_gateway.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity findByEmailAndPassword(String email, String password) throws NoAuthorisationException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPassword(email, password);
        if(optionalUserEntity.isEmpty()){
            throw new NoAuthorisationException("the given user doesn't exist. Please Signup tp conitnue");
        }
        return optionalUserEntity.get();
    }
    public UserEntity registerUser(UserEntity body) throws InvalidRequest {
        if(body.getRole() == Role.ADMIN)throw new InvalidRequest("You cannot create an admin");
        return userRepository.save(body);
    }
}
