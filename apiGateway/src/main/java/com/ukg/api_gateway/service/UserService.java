package com.ukg.api_gateway.service;

import com.ukg.api_gateway.dtos.UserLoginDTO;
import com.ukg.api_gateway.entity.UserEntity;
import com.ukg.api_gateway.exceptions.InvalidRequest;
import com.ukg.api_gateway.exceptions.NoAuthorisationException;
import com.ukg.api_gateway.helper.JWTUtil;
import com.ukg.api_gateway.helper.Role;
import com.ukg.api_gateway.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
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

        String token = jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole());
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
