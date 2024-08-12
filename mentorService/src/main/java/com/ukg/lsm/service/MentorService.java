package com.ukg.lsm.service;

import com.ukg.lsm.dto.MentorCoursesResponse;
import com.ukg.lsm.entity.MentorEntity;

import java.util.List;

public interface MentorService {

    List<MentorEntity> getAllMentors();
    MentorEntity getMentorById(Integer mentorId);
    MentorEntity saveMentor(MentorEntity mentor);
    MentorCoursesResponse GetMentorWithCourse(Long mentorId);


}
