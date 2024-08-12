package com.ukg.lsm.service;
import com.ukg.lsm.entity.CourseEntity;
import com.ukg.lsm.entity.CourseUserEntity;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.repository.CourseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class CourseUserService {
    @Autowired
    private CourseUserRepository courseUserRepository;
    @Autowired
    private CourseService courseService;
    public List<CourseEntity> findCoursesByUserId(Long userId)throws ResourceNotFoundException{
        Optional<List<CourseUserEntity>> optionalEnrolledCourses = courseUserRepository.findByUserId(userId);
        if(optionalEnrolledCourses.isEmpty()){
            throw new ResourceNotFoundException("User has not enrolled in any courses");
        }

        List<Long> courseIds = optionalEnrolledCourses.get().stream()
                .map(CourseUserEntity::getCourseId)
                .toList();

        return courseService.findByCourseIdIn(courseIds);
    }
    public List<Long> findUsersByCourseId(Long courseId) throws ResourceNotFoundException {
        Optional<List<CourseUserEntity>>optionalCourseUserEntities = courseUserRepository.findByCourseId(courseId);
        if(optionalCourseUserEntities.isEmpty()){
            throw new ResourceNotFoundException("No user has enrolled in this course");
        }
        return optionalCourseUserEntities.get().stream()
                .map(CourseUserEntity::getUserId)
                .toList();

    }
}
