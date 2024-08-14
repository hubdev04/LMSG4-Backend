package com.ukg.lsm.service;

import com.ukg.lsm.dto.MentorCoursesResponse;
import com.ukg.lsm.entity.MentorEntity;
import com.ukg.lsm.exceptions.InvalidRequest;
import com.ukg.lsm.exceptions.ResourceNotFoundException;

import java.rmi.RemoteException;
import java.util.List;

public interface MentorService {

    List<MentorEntity> getAllMentors() throws ResourceNotFoundException;

    MentorEntity getMentorById(Integer mentorId) throws ResourceNotFoundException;

    MentorEntity saveMentor(MentorEntity mentor) throws InvalidRequest;

    MentorCoursesResponse GetMentorWithCourse(Long mentorId) throws ResourceNotFoundException;

    //CourseEntity addCourseToMentor(Long mentorId, CourseEntity course);

    //List<CourseEntity> getCoursesByMentor(Long mentorId);
}
