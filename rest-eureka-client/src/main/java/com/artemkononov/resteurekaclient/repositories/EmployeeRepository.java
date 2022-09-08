package com.artemkononov.resteurekaclient.repositories;

import com.artemkononov.resteurekaclient.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Employee> findByDate(LocalDate exactDate);

    List<Employee> findAll();

    Employee getById(Long id);

    void deleteById(Long id);

}
