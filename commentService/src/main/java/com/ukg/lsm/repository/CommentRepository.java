package com.ukg.lsm.repository;
import com.ukg.lsm.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
//    List<Comment> findByUserIdAndCourseIdAndIsDeletedFalse(Long userId, Long courseId);
//    List<Comment> findByCourseIdAndIsDeletedFalse(Long courseId);
//    List<Comment> findByUserIdAndIsDeletedFalse(Long userId);
Optional<Comment> findCommentsByCourseId(Long courseId);


}
