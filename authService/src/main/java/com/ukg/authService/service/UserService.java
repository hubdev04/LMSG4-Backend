package com.ukg.authService.service;

import com.ukg.authService.dtos.UserLoginDTO;
import com.ukg.authService.entity.UserEntity;
import com.ukg.authService.exceptions.InvalidRequest;
import com.ukg.authService.exceptions.NoAuthorisationException;
import com.ukg.authService.helper.JWTUtil;
import com.ukg.authService.helper.Role;
import com.ukg.authService.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;
//
//    @Autowired
//    AuthenticationManager authenticationManager;

    public UserEntity findByEmailAndPassword(String email, String password) throws NoAuthorisationException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPassword(email, password);
        if(optionalUserEntity.isEmpty()){
            throw new NoAuthorisationException("the given user doesn't exist. Please Signup tp conitnue");
        }
        return optionalUserEntity.get();
    }

    public UserEntity findByEmailAndPasswordAndRole(String email, String password, Role role) throws NoAuthorisationException{
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPasswordAndRole(email, password, role);
        if(optionalUserEntity.isEmpty()){
            throw new NoAuthorisationException("the given user doesn't exist. Please Signup tp conitnue");
        }
        return optionalUserEntity.get();
    }

    public UserEntity registerUser(UserEntity body) throws InvalidRequest {
        if(body.getRole() == Role.ADMIN)throw new InvalidRequest("You cannot create an admin");
        return userRepository.save(body);
    }

    public String loginUser(UserLoginDTO body) throws NoAuthorisationException {
        UserEntity existingUser = this.findByEmailAndPasswordAndRole(body.getEmail(), body.getPassword(), body.getRole());
        if(existingUser == null){
            throw new NoAuthorisationException("Credentials don't match. Try with valid credentials");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole(), existingUser.getId());
        System.out.println("Email: " + jwtUtil.getEmail(token));
        System.out.println("Role: " + jwtUtil.getRole(token));

        return token;
//        return jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken()
//        )
    }
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
