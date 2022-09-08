package com.artemkononov.resteurekaclient.controllers;


import com.artemkononov.resteurekaclient.services.EmployeeService;
import com.artemkononov.resteurekaclient.entities.Employee;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Employee> employeeList()
    {
        return  employeeService.list();
    }


    @RequestMapping(value = "/filter/between", method = RequestMethod.POST)
    public List<Employee> filter(@RequestParam("dateFrom")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate dateFrom,
                                 @RequestParam("dateTo")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate dateTo)
    {
        return  employeeService.filter(dateFrom, dateTo);
    }


    @RequestMapping(value = "/filter/exact", method = RequestMethod.POST)
    public List<Employee> filter(@RequestParam(value="exactDate")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate exactDate)
    {
        return employeeService.filter(exactDate);
    }


    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public void employeeAdd(@RequestBody Employee employee)
    {
        employeeService.add(employee);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Employee getById(@PathVariable Long id)
    {
        return employeeService.getById(id);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id)
    {

        employeeService.delete(id);
    }

    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    public void edit(@RequestBody Employee employee)
    {
        employeeService.update(employee);

    }

}
