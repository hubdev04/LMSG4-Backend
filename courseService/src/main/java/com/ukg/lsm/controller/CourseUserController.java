package com.ukg.lsm.controller;

import com.ukg.lsm.configuration.ResponseDTO;
import com.ukg.lsm.dtos.CourseUserPostDto;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.service.CourseUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/courseUser")
@RestController
public class CourseUserController {
    @Autowired
    CourseUserService courseUserService;

    @GetMapping("/{userId}")
    public ResponseDTO getAllCourses(@PathVariable Long userId) throws ResourceNotFoundException {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses fetched successfully")
                .result(courseUserService.findCoursesByUserId(userId))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public ResponseDTO saveCourses(@RequestBody List<CourseUserPostDto> courseUserPostDto)throws Exception{
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses saved successfully")
                .result(courseUserService.saveCourseUser(courseUserPostDto))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseDTO getUsersByCourseId(@PathVariable Long courseId) throws ResourceNotFoundException {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("users retrieved successfully")
                .result(courseUserService.findUsersByCourseId(courseId))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }
}
