package com.ukg.lsm.service;
import com.ukg.lsm.entity.CourseEntity;
import com.ukg.lsm.entity.CourseMentorEntity;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.repository.CourseMentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseMentorService {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseMentorRepository courseMentorRepository;

    public List<CourseEntity> findCoursesByMentorId(Long mentorId) throws ResourceNotFoundException {
        Optional<List<CourseMentorEntity>> optionalCourseMentorEntities = courseMentorRepository.findByMentorId(mentorId);
        if(optionalCourseMentorEntities.isEmpty()){
            throw new ResourceNotFoundException("Couldn't find any courses made by the mentor");
        }
        List<Long>courseIds = optionalCourseMentorEntities.get().stream()
                .map(CourseMentorEntity::getCourseId)
                .toList();

        return courseService.findByCourseIdIn(courseIds);
    }
    public Long findMentorByCourseId(Long courseId) throws ResourceNotFoundException {
        Optional<Long> optionalMentorId  = courseMentorRepository.findByCourseId(courseId);
        if(optionalMentorId.isEmpty()){
            throw new ResourceNotFoundException("No mentor found for this course "+ courseId );
        }
        return optionalMentorId.get();
    }
}
