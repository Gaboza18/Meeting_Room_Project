package com.example.meeting.User.repository;

import com.example.meeting.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
        Optional<User> findUserByUserEmailAndUserName(@Param("user_email") String email , @Param("user_name") String username);
        User findUserByUserEmail(@Param("user_email") String email);
}
