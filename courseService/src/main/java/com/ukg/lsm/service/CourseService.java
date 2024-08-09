package com.ukg.lsm.service;
import com.ukg.lsm.configuration.CourseApprovalStatus;
import com.ukg.lsm.entity.CourseEntity;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> findAllActiveAndApprovedCourses() throws ResourceNotFoundException{
        Optional<List<CourseEntity>> optionalResponse = courseRepository.findByIsActiveTrueAndIsDeletedFalseAndApprovalStatus(CourseApprovalStatus.APPROVED);
        if(optionalResponse.isEmpty()){
            throw new ResourceNotFoundException("No courses found");
        }
        return optionalResponse.get();
    }
    public CourseEntity findActiveAndApprovedCourseById(Long id)throws ResourceNotFoundException{
        Optional<CourseEntity> optionalResponse = courseRepository.findByIdAndIsActiveTrueAndIsDeletedFalseAndApprovalStatus(id, CourseApprovalStatus.APPROVED);
        if(optionalResponse.isEmpty())throw new ResourceNotFoundException("No course with id" + id + " found");
        return optionalResponse.get();
    }
    public List<CourseEntity> findByCourseIdIn(List<Long>courseIds) throws ResourceNotFoundException {
        Optional<List<CourseEntity>> optionalCourseEntities = courseRepository.findByIdIn(courseIds);
        if(optionalCourseEntities.isEmpty()){
            throw new ResourceNotFoundException("No Resource found for the such ids ");
        }
        return optionalCourseEntities.get();
    }
    public List<CourseEntity> findByApprovalStatus(CourseApprovalStatus courseApprovalStatus) throws ResourceNotFoundException {
        Optional<List<CourseEntity>> optionalCourseEntities = courseRepository.findByApprovalStatusAndIsDeletedFalseAndIsActiveTrue(courseApprovalStatus);
        if(optionalCourseEntities.isEmpty()){
            throw new ResourceNotFoundException("No courses with status "+ courseApprovalStatus + " found");
        }
        return optionalCourseEntities.get();
    }
}
