package com.artemkononov.webapplication.services;
import com.artemkononov.webapplication.entities.Department;
import com.artemkononov.webapplication.entities.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@FeignClient(name="rest-eureka-client")
public interface RestService {

    @RequestMapping(value = "employees/list", method = RequestMethod.GET)
    List<Employee> employeeList();

    @RequestMapping(value = "employees/item", method = RequestMethod.POST)
    void add(@RequestBody Employee employee);


    @RequestMapping(value = "employees/filter/exact", method = RequestMethod.POST)
    List<Employee> filter(@RequestParam(value="exactDate")
                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                          LocalDate exactDate);


    @RequestMapping(value = "employees/filter/between", method = RequestMethod.POST)
    List<Employee> filter(@RequestParam("dateFrom")
                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                          LocalDate dateFrom,
                          @RequestParam("dateTo")
                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                          LocalDate dateTo);


    @RequestMapping(value = "employees/item/{id}", method = RequestMethod.GET)
    Employee getById(@PathVariable Long id);


    @RequestMapping(value = "employees/item/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable Long id);

    @RequestMapping(value = "employees/item", method = RequestMethod.PUT)
    public void edit(@RequestBody Employee employee);


    @RequestMapping(value = "departments/list", method = RequestMethod.GET)
    List<Department> departmentList();


    @RequestMapping(value = "departments/add", method = RequestMethod.POST)
    void add(@RequestBody Department department);
}
