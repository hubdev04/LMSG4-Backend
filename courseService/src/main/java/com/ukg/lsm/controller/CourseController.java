package com.ukg.lsm.controller;

import com.ukg.lsm.configuration.ResponseDTO;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.service.CourseMentorService;
import com.ukg.lsm.service.CourseService;
import com.ukg.lsm.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    CourseUserService courseUserService;
    @Autowired
    CourseMentorService courseMentorService;

    @GetMapping("/")
    public ResponseDTO getAllCourses() throws ResourceNotFoundException {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses fetched successfully")
                .result(courseService.findAllActiveAndApprovedCourses())
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }
}
