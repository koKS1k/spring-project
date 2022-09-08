package com.artemkononov.webapplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor

public class Department
{
    private Long id;
    private String name;
    private List<Employee> employees = new ArrayList<>();
    private String averageSalary;

    public void addEmployee(Employee employee){
        this.employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee){
        employee.setDepartment(null);
        this.employees.remove(employee);
    }

    public Department(String name)
    {
        this.name = name;
    }
}
