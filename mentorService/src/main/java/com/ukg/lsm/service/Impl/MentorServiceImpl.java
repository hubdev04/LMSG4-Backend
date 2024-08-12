package com.ukg.lsm.service.Impl;

import com.ukg.lsm.dto.Courses;
import com.ukg.lsm.dto.MentorCoursesResponse;
import com.ukg.lsm.entity.MentorEntity;
import com.ukg.lsm.repository.MentorRepository;
import com.ukg.lsm.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class MentorServiceImpl implements MentorService {


    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<MentorEntity> getAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public MentorEntity getMentorById(Integer mentorId) {
        return mentorRepository.findById(mentorId).get();
    }

    @Override
    public MentorEntity saveMentor(MentorEntity mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public MentorCoursesResponse GetMentorWithCourse(Long mentorId) {
        return null;
    }

}

