package com.ukg.lsm.controller;

import com.ukg.lsm.configuration.ResponseDTO;
import com.ukg.lsm.dtos.CommentPostDto;
import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")

public class CommentController {

    private final CommentService commentService;

    // Constructor to autowire the CommentService
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseDTO saveComments(@RequestBody List<CommentPostDto> listCommentPostDto) {
        System.out.println("inseide post controller of comments");
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
        System.out.println("\n\n inside get comments controller\n\n");
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
        System.out.println("courseId----" + courseId + "\n");
        System.out.println("userId------" + userId + "\n");
        //System.out.println("\n\n   insisde course user api \n\n");
        List<Comment> comments = commentService.findCommentsByCourseIdAndUserId(courseId, userId);

        return ResponseDTO.builder()
                .success(true)
                .message("Comments fetched successfully")
                .result(comments)
                .completionTimeStamp(LocalDateTime.now())
                .build();
    }


}
