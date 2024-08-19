package com.ukg.lsm.service;
import java.util.*;

import com.ukg.lsm.dtos.CommentPostDto;
import com.ukg.lsm.entity.Comment;
import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.ResourceNotFoundException;

public interface CommentService {

    //Comment addComment(Comment comment);


      public List<Comment>postComments(List<CommentPostDto> listCommentPostDto)throws InvalidRequest;

      List<Comment> findAllActiveAndNonDeletedComments() throws ResourceNotFoundException;

      List<Comment> findCommentsByCourseIdAndUserId(Long courseId, Long userId) throws ResourceNotFoundException;

}


