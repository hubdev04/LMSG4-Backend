package com.ukg.lsm.controller;

import com.ukg.lsm.entity.MentorEntity;
import com.ukg.lsm.exceptions.InvalidRequest;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.service.MentorService;
import com.ukg.lsm.utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/train/mentor")
@RestController
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllMentors() throws ResourceNotFoundException {

        List<MentorEntity> mentors = mentorService.getAllMentors();
        ResponseDTO responseDTO=ResponseDTO.builder()
                .success(true)
                .message("Mentor Fetched")
                .completionTimeStamp(LocalDateTime.now())
                .results(mentors)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseDTO getMentorById(@PathVariable int id) throws ResourceNotFoundException {
        System.out.println("sumit");
        return ResponseDTO.builder()
                .success(true)
                .message("Mentor found")
                .completionTimeStamp(LocalDateTime.now())
                .results(id)
                .build();
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> saveMentor(@RequestBody MentorEntity mentor) throws InvalidRequest {
        MentorEntity mentorEntity = mentorService.saveMentor(mentor);

        ResponseDTO responseDTO=ResponseDTO.builder()
                .success(true)
                .message("Mentor successfully Created")
                .completionTimeStamp(LocalDateTime.now())
                .results(mentorEntity)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
/*
    @GetMapping("/{id}/courses")
    public MentorCoursesResponse GetMentorWithCourses(@PathVariable("id") int mentorId) {

        MentorEntity mentor = mentorService.getMentorById(mentorId);
        MentorCoursesResponse mentorCoursesResponse = new MentorCoursesResponse();
        mentorCoursesResponse.setMentor(mentor);
        return mentorCoursesResponse;

    }


    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseEntity>> getCoursesByMentor(@PathVariable("id") Long mentorId) {
        List<CourseEntity> courses = mentorService.getCoursesByMentor(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<CourseEntity> addCourseToMentor(@PathVariable("id") Long mentorId, @RequestBody CourseEntity course) {
        CourseEntity addedCourse = mentorService.addCourseToMentor(mentorId, course);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCourse);
    }
    */

}
