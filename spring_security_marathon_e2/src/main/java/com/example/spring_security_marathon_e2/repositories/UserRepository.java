package com.example.spring_security_marathon_e2.repositories;

import com.example.spring_security_marathon_e2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            SELECT u FROM User AS u WHERE u.username = :username
            """)
    Optional<User> findUserByUsername(String username);
}
