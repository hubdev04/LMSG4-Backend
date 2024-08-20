package com.ukg.lsm.service;

import com.ukg.lsm.dtos.CommentPostDto;
import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final RestTemplate restTemplate;
    private static final String COURSE_SERVICE_URL = "http://localhost:8081/courses/";

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
    }

    private void validateCourseId(Long courseId) throws InvalidRequest {
        logger.info("Validating courseId inside validation: {}", courseId);
        try {
        ResponseEntity<String> response = restTemplate.getForEntity(COURSE_SERVICE_URL + courseId, String.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                System.out.println(response.getStatusCode());
            }
        }
        catch (Exception ex){
            throw new InvalidRequest("Invalid courseId: " + courseId);
        }
        logger.info("CourseId: {} is valid", courseId);
    }


    private Comment mapDtoToEntity(CommentPostDto dto) {
        logger.info("Mapping CommentPostDto to Comment entity for userId: {} and courseId: {}", dto.getUserId(), dto.getCourseId());
        return Comment.builder()
                .courseId(dto.getCourseId())
                .commentData(dto.getCommentData())
                .isActive(true)
                .isDeleted(false)
                .userId(dto.getUserId())
                .build();
    }

    @Override
    public List<Comment> postComments(List<CommentPostDto> listCommentPostDto) throws InvalidRequest {
        logger.info("Posting {} comments", listCommentPostDto.size());
        for (CommentPostDto dto : listCommentPostDto) {
            logger.info("Processing comment for courseId: {} and userId: {}", dto.getCourseId(), dto.getUserId());
            validateCourseId(dto.getCourseId());
        }
        List<Comment> comments = listCommentPostDto.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
        return commentRepository.saveAll(comments);
    }

    public List<Comment> findAllActiveAndNonDeletedComments() throws ResourceNotFoundException {
        logger.info("Fetching all active and non-deleted comments");
        Optional<List<Comment>> optionalComments = commentRepository.findByIsActiveTrueAndIsDeletedFalse();
        if (optionalComments.isEmpty()) {
            logger.warn("No active and non-deleted comments found");
            throw new ResourceNotFoundException("No comments found");
        }
        return optionalComments.get();
    }

    @Override
    public List<Comment> findCommentsByCourseIdAndUserId(Long courseId, Long userId) throws ResourceNotFoundException {
        logger.info("Fetching comments for courseId: {} and userId: {}", courseId, userId);
        Optional<List<Comment>> optionalComments;


        if (courseId != null && userId != null) {
            logger.info("Fetching comments by both courseId and userId");
            optionalComments = commentRepository.findByCourseIdAndUserIdAndIsActiveTrueAndIsDeletedFalse(courseId, userId);
        } else if (courseId != null) {
            logger.info("Fetching comments by courseId");
            optionalComments = commentRepository.findByCourseIdAndIsActiveTrueAndIsDeletedFalse(courseId);
        } else if (userId != null) {
            logger.info("Fetching comments by userId");
            optionalComments = commentRepository.findByUserIdAndIsActiveTrueAndIsDeletedFalse(userId);
        } else {
            logger.warn("No courseId or userId provided");
            throw new ResourceNotFoundException("No courseId or userId provided");
        }
        if (optionalComments.isEmpty()) {
            logger.warn("No comments found for courseId: {} and userId: {}", courseId, userId);
            throw new ResourceNotFoundException("No comments found");
        }
        logger.info("Found {} comments for courseId: {} and userId: {}", optionalComments.get().size(), courseId, userId);
        return optionalComments.get();
    }


}



