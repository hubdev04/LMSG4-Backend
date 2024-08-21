package com.ukg.lsm.controller;

import com.ukg.lsm.configuration.ResponseDTO;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.service.CourseMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/courseMentor")
public class CourseMentorController {
    @Autowired
    CourseMentorService courseMentorService;

    @GetMapping("/{mentorId}")
    public ResponseDTO getAllCoursesByMentor(@PathVariable Long mentorId) throws ResourceNotFoundException {
        System.out.println("hello inside courseMentor contoller.");
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses by mentor fetched successfully")
                .result(courseMentorService.findCoursesByMentorId(mentorId))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }
    @GetMapping("/course/{courseId}")
    public ResponseDTO getMentorByCourseId(@PathVariable Long courseId) throws ResourceNotFoundException {
        System.out.println("hello inside courseMentor contoller.");
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses by mentor fetched successfully")
                .result(courseMentorService.findMentorByCourseId(courseId))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

}
