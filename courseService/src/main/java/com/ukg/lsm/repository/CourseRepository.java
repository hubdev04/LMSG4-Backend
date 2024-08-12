package com.ukg.lsm.repository;
import com.ukg.lsm.configuration.CourseApprovalStatus;
import com.ukg.lsm.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
   Optional<List<CourseEntity>> findByIsActiveTrueAndIsDeletedFalseAndApprovalStatus(CourseApprovalStatus CourseApprovalStatus);
    Optional<CourseEntity> findByIdAndIsActiveTrueAndIsDeletedFalseAndApprovalStatus(Long id, CourseApprovalStatus approvalStatus);
    Optional<List<CourseEntity>> findByApprovalStatusAndIsDeletedFalseAndIsActiveTrue(CourseApprovalStatus approvalStatus);
    Optional<CourseEntity> findById(Long courseId);
    Optional<List<CourseEntity>> findByIdIn(List<Long> courseIds);

}
