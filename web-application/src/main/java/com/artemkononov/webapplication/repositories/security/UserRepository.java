package com.artemkononov.webapplication.repositories.security;

import com.artemkononov.webapplication.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}