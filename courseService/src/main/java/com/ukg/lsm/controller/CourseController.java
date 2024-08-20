package com.ukg.lsm.controller;
import com.ukg.lsm.configuration.ResponseDTO;
import com.ukg.lsm.dtos.CoursePostDto;
import com.ukg.lsm.dtos.CourseStatusChangeDto;
import com.ukg.lsm.exceptions.InvalidRequest;
import com.ukg.lsm.exceptions.ResourceNotFoundException;
import com.ukg.lsm.service.CourseMentorService;
import com.ukg.lsm.service.CourseService;
import com.ukg.lsm.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    CourseUserService courseUserService;
    @Autowired
    CourseMentorService courseMentorService;

    @GetMapping("/")
    public ResponseDTO getAllCourses() throws ResourceNotFoundException {
        System.out.println("\n\n hello hello \n\n" );
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("courses fetched successfully")
                .result(courseService.findAllActiveAndApprovedCourses())
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/")
    public ResponseDTO saveCourse(@RequestBody List<CoursePostDto> listCoursePostDto) throws Exception{
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("Courses saved successfully")
                .result(courseService.postCourses(listCoursePostDto))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{courseId}")
    public ResponseDTO saveCourse(@PathVariable Long courseId)throws Exception{
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("Course fetched successfully")
                .result(courseService.findActiveAndApprovedCourseById(courseId))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @PutMapping
    public ResponseDTO statusChange(@RequestBody List<CourseStatusChangeDto> courseStatusChangeDtos) throws ResourceNotFoundException, InvalidRequest {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("status of all the courses changed successfully")
                .result(courseService.saveStatusChange(courseStatusChangeDtos))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/delete")
    public ResponseDTO softDelete(@RequestBody List<Long> courseIds) throws ResourceNotFoundException, InvalidRequest {
        return ResponseDTO.builder()
                .success(true)
                .errorDetails(null)
                .message("soft delete of all the courses changed successfully")
                .result(courseService.softDeleteCourses(courseIds))
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }
}
