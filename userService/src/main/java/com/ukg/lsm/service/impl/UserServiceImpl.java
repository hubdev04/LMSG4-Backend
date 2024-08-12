package com.ukg.lsm.service.impl;

import com.ukg.lsm.dto.CourseDto;
import com.ukg.lsm.dto.CourseUserDto;
import com.ukg.lsm.entity.User;
import com.ukg.lsm.repository.UserRepository;
import com.ukg.lsm.service.UserService;
import com.ukg.lsm.utils.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;


    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User createUser(User user)  throws InvalidRequestException {

        //User saveUser = ;
        try{
        return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new InvalidRequestException("Email already exists");
        }
    }
    public List<CourseUserDto> getCoursesByUserId(Long Id){
        String url = "http://localhost:8080/users/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>(Id, headers);
        ResponseEntity<List<CourseUserDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CourseUserDto>>() {}
        );
        return response.getBody();
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public List<CourseDto> getAllCourses(){
        String url = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>( headers);
        ResponseEntity<List<CourseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<CourseDto>>() {}
        );

        return response.getBody();

    }
    public CourseDto getCourseById(Long Id){
        String url="";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>(Id, headers);
        ResponseEntity<CourseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<CourseDto>() {}
        );
        return response.getBody();
    }


}
