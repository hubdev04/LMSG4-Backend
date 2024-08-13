package com.ukg.lsm.service.impl;

import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.repository.CommentRepository;
import com.ukg.lsm.service.CommentService;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
public Optional<Comment> getCommentById(Long commentId){
        return commentRepository.findById(commentId);
}
    public Optional<Comment> getCommentsByCourseId(Long courseId){
        return commentRepository.findCommentsByCourseId(courseId);
    }
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
    public void deleteCommentByCourseId(Long courseId ){
        Optional<Comment> comments = getCommentsByCourseId(courseId);
        commentRepository.deleteById(courseId);
    }
    public Optional<Comment> getCommentsByCourseIdUserId(Long courseId , Long userId){
        return commentRepository.findCommentsByCourseId(courseId);
    }

}
