package com.ukg.lsm.service.impl;

import com.ukg.lsm.entity.User;
import com.ukg.lsm.repository.UserRepository;
import com.ukg.lsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;


    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User createUser(User user) {

        //User saveUser = ;
        return userRepository.save(user);
    }
    public List<Course>  getCoursesByUserId(Long Id){
        String url = "http://localhost:8080/users/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> request = new HttpEntity<>(Id, headers);
        ResponseEntity<List<Course>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Course>>() {}
        );
        return response.getBody();
    }

}
