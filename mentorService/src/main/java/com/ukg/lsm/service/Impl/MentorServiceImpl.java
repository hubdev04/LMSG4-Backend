package com.ukg.lsm.service.Impl;

import com.ukg.lsm.dto.MentorCoursesResponse;
import com.ukg.lsm.entity.MentorEntity;
import com.ukg.lsm.exceptions.InvalidRequest;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.repository.MentorRepository;
import com.ukg.lsm.service.MentorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorRepository mentorRepository;


    @Override
    public List<MentorEntity> getAllMentors() throws ResourceNotFoundException {
        List<MentorEntity> response=mentorRepository.findAll();

        if(response.isEmpty()){
            throw new ResourceNotFoundException("No Mentor found");
        }
        return response;
    }


    @Override
    public MentorEntity getMentorById(Integer mentorId) throws ResourceNotFoundException {
        Optional<MentorEntity> response = Optional.of(mentorRepository.findById(mentorId).get());

        throw new ResourceNotFoundException("No such mentor found");
    }

    @Override
    @Transactional
    public MentorEntity saveMentor(MentorEntity mentor) throws InvalidRequest {

        return mentorRepository.save(mentor);
    }


    @Override
    public MentorCoursesResponse GetMentorWithCourse(Long mentorId) {
        return null;
    }


   /* @Override
    public CourseEntity addCourseToMentor(Long mentorId, CourseEntity course) {
        MentorEntity mentor = getMentorById(mentorId);
        course.setMentor(mentor);
        return courseRepository.save(course);
    }

    @Override
    public List<CourseEntity> getCoursesByMentor(Long mentorId) {
        return courseRepository.findByMentorId(mentorId);
    }

    */

}

