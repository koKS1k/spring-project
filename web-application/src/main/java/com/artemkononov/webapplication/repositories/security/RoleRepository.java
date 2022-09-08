package com.artemkononov.webapplication.repositories.security;

import com.artemkononov.webapplication.entities.security.Role;
import com.artemkononov.webapplication.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
