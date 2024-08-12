package com.ukg.lsm.controller;

import com.ukg.lsm.dto.MentorCoursesResponse;
import com.ukg.lsm.entity.MentorEntity;
import com.ukg.lsm.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/train/mentor")
@RestController
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<MentorEntity>> getAllMentors() {
        List<MentorEntity> mentors = mentorService.getAllMentors();
        return ResponseEntity.status(HttpStatus.OK).body(mentors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorEntity> getMentorById(@PathVariable("id") int mentorId) {
        MentorEntity mentor = mentorService.getMentorById(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(mentor);
    }

    @PostMapping
    public ResponseEntity<MentorEntity> saveMentor(@RequestBody MentorEntity mentor) {
        MentorEntity mentorEntity = mentorService.saveMentor(mentor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorEntity);
    }

    @GetMapping("/{id}/courses")
    public MentorCoursesResponse GetMentorWithCourses(@PathVariable("id") int mentorId) {

        MentorEntity mentor = mentorService.getMentorById(mentorId);
        MentorCoursesResponse mentorCoursesResponse = new MentorCoursesResponse();
        mentorCoursesResponse.setMentor(mentor);
        return mentorCoursesResponse;

    }

}
