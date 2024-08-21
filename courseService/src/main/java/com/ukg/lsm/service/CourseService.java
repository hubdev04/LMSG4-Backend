package com.ukg.lsm.service;
import com.ukg.lsm.configuration.CourseApprovalStatus;
import com.ukg.lsm.dtos.CoursePostDto;
import com.ukg.lsm.dtos.CourseStatusChangeDto;
import com.ukg.lsm.entity.CourseEntity;
import com.ukg.lsm.entity.CourseMentorEntity;
import com.ukg.lsm.exceptions.InvalidRequest;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.repository.CourseMentorRepository;
import com.ukg.lsm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMentorRepository courseMentorRepository;

    public List<CourseEntity> findAllActiveAndApprovedCourses() throws ResourceNotFoundException{
        Optional<List<CourseEntity>> optionalResponse = courseRepository.findByIsActiveTrueAndIsDeletedFalse();
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
    public List<CourseEntity> postCourses(List<CoursePostDto> courses){

        List<CourseEntity>savedCourses =  courseRepository.saveAll(courses.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList()));


        courseMentorRepository.saveAll(savedCourses.stream()
                .map(this::mapSecondDtoToEntity)
                .collect(Collectors.toList())
        );

        return savedCourses;
    }
    public List<CourseEntity> saveStatusChange(List<CourseStatusChangeDto> courseStatusChangeDtos) throws ResourceNotFoundException, InvalidRequest {
        List<CourseEntity> coursesWhoseStatusNeedToBeChanged = new ArrayList<>();
        for(CourseStatusChangeDto courseStatusChangeDto : courseStatusChangeDtos){
            Long id = courseStatusChangeDto.getCourseId();
            Optional<CourseEntity> optionalCourseEntity = courseRepository.findById(id);

            if(optionalCourseEntity.isEmpty())throw new ResourceNotFoundException("no exception with such id found" + id);
            else{
                CourseEntity courseWhoseStatusNeedToBeChanged = optionalCourseEntity.get();
                if(courseWhoseStatusNeedToBeChanged.getApprovalStatus() == CourseApprovalStatus.APPROVED || courseWhoseStatusNeedToBeChanged.getApprovalStatus() == CourseApprovalStatus.DENIED){
                    throw new InvalidRequest("you cannot change the status of course which is approved or denied");
                }
                courseWhoseStatusNeedToBeChanged.setApprovalStatus(courseStatusChangeDto.getStatus());
                courseWhoseStatusNeedToBeChanged.setLastModifiedDate(LocalDate.now());
                courseWhoseStatusNeedToBeChanged.setLastModifiedBy(courseStatusChangeDto.getAdminId());
                coursesWhoseStatusNeedToBeChanged.add(courseWhoseStatusNeedToBeChanged);
            }
        }
        return courseRepository.saveAll(coursesWhoseStatusNeedToBeChanged);
    }

    public List<CourseEntity> softDeleteCourses(List<Long>courseIds) throws ResourceNotFoundException {
        List<CourseEntity> courseEntitiesToBeDeleted = new ArrayList<>();
        for(Long courseId : courseIds){
            findActiveAndApprovedCourseById(courseId);
            Optional<CourseEntity> optionalCourseEntity = courseRepository.findById(courseId);
            CourseEntity courseEntityToBeDeleted = optionalCourseEntity.get();
            courseEntityToBeDeleted.setIsDeleted(true);
            courseEntityToBeDeleted.setIsActive(false);
            courseEntitiesToBeDeleted.add(courseEntityToBeDeleted);
        }
        return courseRepository.saveAll(courseEntitiesToBeDeleted);
    }
    public CourseEntity mapDtoToEntity(CoursePostDto dto){
        return CourseEntity.builder()
                .title(dto.getTitle())
                .approvalStatus(CourseApprovalStatus.PENDING)
                .createdBy(dto.getCreatedBy())
                .createdDate(LocalDate.now())
                .isDeleted(false)
                .isActive(true)
                .category(dto.getCategory())
                .duration((long) dto.getDuration())
                .build();
    }
    public CourseMentorEntity mapSecondDtoToEntity(CourseEntity dto){
        return CourseMentorEntity.builder()
                .courseId(dto.getId())
                .mentorId(dto.getCreatedBy())
                .createdDate(LocalDate.now())
                .isDeleted(false)
                .isActive(true)
                .build();
    }
}
