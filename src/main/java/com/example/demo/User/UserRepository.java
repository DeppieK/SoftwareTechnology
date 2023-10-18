package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    List<UserEntity> findByUserId(@Param("id") long id);
    Optional<UserEntity> findByEmail(String email);

    UserEntity findByUsernameAndPassword(String username, String password);

}
