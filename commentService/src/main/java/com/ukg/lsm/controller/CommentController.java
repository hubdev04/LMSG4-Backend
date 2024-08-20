package com.ukg.lsm.controller;

import com.ukg.lsm.dtos.ResponseDTO;
import com.ukg.lsm.dtos.CommentPostDto;
import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ukg.lsm.exception.InvalidRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")

public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseDTO saveComments(@RequestBody List<CommentPostDto> listCommentPostDto) throws InvalidRequest {

        logger.info("Received request to save {} comments", listCommentPostDto.size());

            return ResponseDTO.builder()
                    .success(true)
                    .errorDetails(null)
                    .message("Comments saved successfully")
                    .result(commentService.postComments(listCommentPostDto))
                    .completionTimeStamp(LocalDateTime.now())
                    .build();
    }



    @GetMapping
    public ResponseDTO getAllComments() throws ResourceNotFoundException {
        logger.info("Received request to fetch all active and non-deleted comments");

        return ResponseDTO.builder()
                .success(true)
                .message("Comments fetched successfully")
                .result(commentService.findAllActiveAndNonDeletedComments())  // Fetching comments using the service
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/courseUser")
    public ResponseDTO getCommentsByCourseAndUser(
            @RequestParam (required = false) Long courseId,
            @RequestParam(required = false) Long userId) throws ResourceNotFoundException {
        logger.info("Received request to fetch comments for courseId: {} and userId: {}", courseId, userId);

        List<Comment> comments = commentService.findCommentsByCourseIdAndUserId(courseId, userId);
        logger.info("Successfully fetched {} comments for courseId: {} and userId: {}", comments.size(), courseId, userId);


        return ResponseDTO.builder()
                .success(true)
                .message("Comments fetched successfully")
                .result(comments)
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }


}
