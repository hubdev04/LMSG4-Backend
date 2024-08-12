package com.ukg.lsm.service.impl;

import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.repository.CommentRepository;
import com.ukg.lsm.service.CommentService;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }


}
