package com.artemkononov.resteurekaclient.repositories;

import com.artemkononov.resteurekaclient.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAll();

    Department getById(Long id);

    Department getByName(String name);

    Department findByName(String name);
}
