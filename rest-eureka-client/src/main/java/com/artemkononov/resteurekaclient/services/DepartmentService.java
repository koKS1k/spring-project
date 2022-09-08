package com.artemkononov.resteurekaclient.services;

import com.artemkononov.resteurekaclient.entities.Department;
import com.artemkononov.resteurekaclient.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> list()
    {
        return departmentRepository.findAll();
    }

    public Department getByName(String name)
    {
        return  departmentRepository.getByName(name);
    }

    public void add(Department department)
    {
        departmentRepository.save(department);
    }
}
