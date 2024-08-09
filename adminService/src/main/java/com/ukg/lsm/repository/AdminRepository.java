package com.ukg.lsm.repository;
import com.ukg.lsm.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

//    public boolean existsByEmail(String email);
}
