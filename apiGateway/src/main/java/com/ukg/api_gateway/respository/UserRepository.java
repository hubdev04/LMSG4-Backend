package com.ukg.api_gateway.respository;

import com.ukg.api_gateway.entity.UserEntity;
import com.ukg.api_gateway.helper.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   public Optional<UserEntity> findByEmailAndPassword(String email, String password);

   public Optional<UserEntity> findByEmail(String email);

   public Optional<UserEntity> findByEmailAndPasswordAndRole(String email, String password, Role role);
}
