package com.ukg.lsm.repository;
import com.ukg.lsm.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<List<Comment>> findByCourseId(Long courseId);

    Optional<List<Comment>> findByCourseIdAndUserId(Long courseId, Long userId);

    Optional<List<Comment>> findByIsActiveTrueAndIsDeletedFalse();

    Optional<List<Comment>> findByCourseIdAndUserIdAndIsActiveTrueAndIsDeletedFalse(Long courseId, Long userId);

    Optional<List<Comment>> findByCourseIdAndIsActiveTrueAndIsDeletedFalse(Long courseId);

    Optional<List<Comment>> findByUserIdAndIsActiveTrueAndIsDeletedFalse(Long userId);


}


