package com.ukg.lsm.service;

import com.ukg.lsm.dtos.CommentPostDto;
import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.exception.ResourceNotFoundException;
import com.ukg.lsm.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
//
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    private Comment mapDtoToEntity(CommentPostDto dto) {
        return Comment.builder()
                        .courseId(dto.getCourseId())
                        .commentData(dto.getCommentData())
                        .isActive(true)
                        .isDeleted(false)
                        .userId(dto.getUserId())
                        .build();
    }

    public List<Comment> postComments(List<CommentPostDto> listCommentPostDto) {
        List<Comment> comments = listCommentPostDto.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
        return commentRepository.saveAll(comments);
    }
    public List<Comment> findAllActiveAndNonDeletedComments() throws ResourceNotFoundException {
        // Fetch the comments using the repository method
        Optional<List<Comment>> optionalComments = commentRepository.findByIsActiveTrueAndIsDeletedFalse();

        // Check if comments are present; otherwise, throw an exception
        if (optionalComments.isEmpty()) {
            throw new ResourceNotFoundException("No comments found");
        }
        return optionalComments.get();
    }
    @Override
    public List<Comment> findCommentsByCourseIdAndUserId(Long courseId, Long userId) throws ResourceNotFoundException {
        System.out.println("12345");
        Optional<List<Comment>> optionalComments;

        System.out.println("12345");
        if (courseId != null && userId != null) {
            // Fetch comments by both courseId and userId
            optionalComments = commentRepository.findByCourseIdAndUserIdAndIsActiveTrueAndIsDeletedFalse(courseId, userId);
        } else if (courseId != null) {
            // Fetch comments by courseId only
            optionalComments = commentRepository.findByCourseIdAndIsActiveTrueAndIsDeletedFalse(courseId);
        } else if (userId != null) {
            // Fetch comments by userId only
            optionalComments = commentRepository.findByUserIdAndIsActiveTrueAndIsDeletedFalse(userId);
        } else {
            throw new ResourceNotFoundException("No courseId or userId provided");
        }

        // Check if comments are present and not empty
        if (optionalComments.isEmpty()) {
            throw new ResourceNotFoundException("No comments found");
        }
        return optionalComments.get();
    }




//
//
//    @Override
//    public Comment addComment(Comment comment) {
//        comment.setIsActive(true);
//        comment.setIsDeleted(false);
//        return commentRepository.save(comment);
//
//    }
//public Optional<Comment> getCommentById(Long commentId){
//    return commentRepository.findById(commentId)
//            .filter(comment -> !comment.getIsDeleted() && comment.getIsActive());
//
//}
//    public List<Comment> getCommentsByCourseId(Long courseId){
//        return commentRepository.findCommentsByCourseId(courseId).stream()
//                .filter(comment -> !comment.getIsDeleted() && comment.getIsActive())
//                .collect(Collectors.toList());
//
//    }
//public List<Comment> softDeleteComments(List<Long> commentIds) throws ResourceNotFoundException {
//    List<Comment> commentsToBeDeleted = new ArrayList<>();
//    for(Long commentId : commentIds){
//        Optional<Comment> optionalComment = commentRepository.findById(commentId);
//        if(optionalComment.isPresent()){
//            Comment commentToBeDeleted = optionalComment.get();
//            commentToBeDeleted.setIsActive(true);
//            commentToBeDeleted.setIsDeleted(false);
//            commentsToBeDeleted.add(commentToBeDeleted);
//        } else {
//            // Handle the case where the comment with the given ID does not exist
//            throw new ResourceNotFoundException("Comment with ID " + commentId + " not found.");
//        }
//    }
//    return commentRepository.saveAll(commentsToBeDeleted);
//}
//
//
//
//
//    public List<Comment> getCommentsByCourseIdUserId(Long courseId , Long userId){
//        return commentRepository.findCommentsByCourseIdUserId(courseId, userId).stream()
//                .filter(comment -> !comment.getIsDeleted() && comment.getIsActive())
//                .collect(Collectors.toList());
//
//
//    }
//
}
