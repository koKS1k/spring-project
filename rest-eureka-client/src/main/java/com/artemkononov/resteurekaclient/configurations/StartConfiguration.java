package com.artemkononov.resteurekaclient.configurations;

import com.artemkononov.resteurekaclient.entities.Department;
import com.artemkononov.resteurekaclient.entities.Employee;
import com.artemkononov.resteurekaclient.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StartConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
        List<Employee> employeeList= new ArrayList<>();

        LocalDate date1 = LocalDate.of(1987, 12, 31);
        LocalDate date2 = LocalDate.of(1999, 6, 15);
        LocalDate date3 = LocalDate.of(1875, 3, 3);

        Department department= new Department("Logistics");
        Employee employee1 = new Employee(department, "Petrov Ivan Sidorovich",date1,25000);
        Employee employee2 = new Employee(department, "Ivanov Sergei Sidorovich",date2,15000);
        Employee employee3 = new Employee(department, "Pavel Semenovich Evdoseev",date3,55000);

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        return args -> {
            employeeRepository.saveAll(employeeList);
        };
    }
}
