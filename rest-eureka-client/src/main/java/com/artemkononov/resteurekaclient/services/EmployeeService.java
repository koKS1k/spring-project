package com.artemkononov.resteurekaclient.services;


import com.artemkononov.resteurekaclient.entities.Department;
import com.artemkononov.resteurekaclient.entities.Employee;
import com.artemkononov.resteurekaclient.repositories.DepartmentRepository;
import com.artemkononov.resteurekaclient.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    public void add(Employee employee) {

        String departmentName = employee.getDepartment().getName();
        Department existDepartment = departmentRepository.findByName(departmentName);
        if (existDepartment != null)
            employee.setDepartment(existDepartment);

        employee.setAge(Period.between(employee.getDate(), LocalDate.now()).getYears());
        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.getById(id);
        employee.setDepartment(null);
        employeeRepository.save(employee);
        employeeRepository.deleteById(id);
    }

    public void update(Employee employee) {
        Optional<Employee> row = employeeRepository.findById(employee.getId());
        if (row.isPresent()) {
            Employee updatedEmployee = row.get();

            if (!employee.getName().isEmpty())
                updatedEmployee.setName(employee.getName());

            if (employee.getDate() != null)
                updatedEmployee.setDate(employee.getDate());

            if (employee.getSalary() != null)
                updatedEmployee.setSalary(employee.getSalary());

            if (employee.getDepartment() != null) {
                String departmentName = employee.getDepartment().getName();
                Department existDepartment = departmentRepository.findByName(departmentName);
                if (existDepartment != null)
                    updatedEmployee.setDepartment(existDepartment);
                if (existDepartment == null)
                    updatedEmployee.setDepartment(employee.getDepartment());
            }
            employeeRepository.save(updatedEmployee);
        }

    }

    public List<Employee> filter(LocalDate dateFrom, LocalDate dateTo) {
        return employeeRepository.findAllByDateBetween(dateFrom, dateTo);
    }

    public List<Employee> filter(LocalDate exactDate) {
        return employeeRepository.findByDate(exactDate);
    }

    public Employee getById(Long id) {
        return employeeRepository.getById(id);
    }
}
