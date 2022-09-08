package com.artemkononov.resteurekaclient.controllers;

import com.artemkononov.resteurekaclient.entities.Department;
import com.artemkononov.resteurekaclient.services.DepartmentService;
import com.artemkononov.resteurekaclient.entities.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path="list")
    public List<Department> departmentList()
    {
        return departmentService.list();
    }

    @PostMapping(path= "add")
    public void departmentAdd(@RequestBody Department department)
    {
        departmentService.add(department);
    }
}
