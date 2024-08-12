package com.ukg.lsm.repository;
import com.ukg.lsm.entity.CourseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUserEntity, Long> {
    Optional<List<CourseUserEntity>> findByUserId(Long userId);
    Optional<List<CourseUserEntity>> findByCourseId(Long courseId);
}
