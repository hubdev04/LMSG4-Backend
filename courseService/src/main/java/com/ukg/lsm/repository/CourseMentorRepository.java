package com.ukg.lsm.repository;

import com.ukg.lsm.entity.CourseMentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseMentorRepository extends JpaRepository<CourseMentorEntity, Long> {
    Optional<List<CourseMentorEntity>> findByMentorId(Long mentorId);
    Optional<Long>findByCourseId(Long courseId);
}
