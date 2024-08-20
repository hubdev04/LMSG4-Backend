package com.ukg.authService.respository;

import com.ukg.authService.entity.UserEntity;
import com.ukg.authService.helper.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   public Optional<UserEntity> findByEmailAndPassword(String email, String password);

   public Optional<UserEntity> findByEmail(String email);

   public Optional<UserEntity> findByEmailAndPasswordAndRole(String email, String password, Role role);
}
