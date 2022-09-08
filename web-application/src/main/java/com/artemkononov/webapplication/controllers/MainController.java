package com.artemkononov.webapplication.controllers;

import com.artemkononov.webapplication.entities.Department;
import com.artemkononov.webapplication.entities.Employee;
import com.artemkononov.webapplication.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Controller
@RequestMapping("app")
public class MainController {

    RestService restService;

   @Autowired
   public MainController(RestService restService) {
        this.restService = restService;
    }

    /*-------------------MAIN------------------*/
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model)
    {
       model.addAttribute("employeeList", restService.employeeList());
       return "employee-list";
    }
    /*-----------------------------------------*/


    /*----------------DEPARTMENTS--------------*/
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String departments(Model model)
    {
        String averageSalary;

        List<Department> departmentList= restService.departmentList();

        for (Department department: departmentList)
        {
            List<Employee> employeeList=restService.employeeList();
            OptionalDouble sumSalary= employeeList.stream()
                    .filter(e -> e.getDepartment().getName().equals(department.getName()))
                    .map(Employee::getSalary)
                    .mapToLong(Long::longValue)
                    .average();

            if(sumSalary.isPresent())
                //Форматируем так, чтобы тыбо 2 точки после запятой.
                averageSalary= String.format("%.2f", sumSalary.getAsDouble());
            else
                averageSalary= "--";

           department.setAverageSalary(averageSalary);
        }

        model.addAttribute("departmentList", departmentList);
        return "department-list";
    }
    /*-----------------------------------------*/

    /*-------------DEPARTMENTS-STUFF-----------*/
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public String departments(Model model, @PathVariable Long id)
    {
        List<Employee> employees=restService.employeeList().stream()
                .filter(employee -> employee.getDepartment().getId().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("employeeList",employees);
        return "employee-list";
    }

    /*-----------------------------------------*/


    /*-------------------LIST-------------------*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> employeeList()
    {
        return restService.employeeList();
    }
    /*------------------------------------------*/


    /*--------------FILTER_BETWEEN--------------*/
    @RequestMapping(value = "/filter/between", method = RequestMethod.POST)
    public String filter(Model model,
                         @RequestParam("dateFrom")
                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                         LocalDate dateFrom,
                         @RequestParam("dateTo")
                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                         LocalDate dateTo)
    {
        model.addAttribute("employeeList", restService.filter(dateFrom,dateTo));
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "employee-list";
    }
    /*-----------------------------------------*/


    /*--------------FILTER_EXACT---------------*/
    @RequestMapping(value = "/filter/exact", method = RequestMethod.POST)
    public String filter( Model model,
                          @RequestParam("exactDate")
                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                          LocalDate exactDate)
    {
        model.addAttribute("employeeList", restService.filter(exactDate));
        model.addAttribute("exactDate", exactDate);
        return "employee-list";
    }
    /*-----------------------------------------*/


    /*------------------NEW-------------------*/
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newEmployee(@ModelAttribute("employee")Employee employee){

        restService.add(employee);
        return "redirect:/app/main";
    }
    /*-----------------------------------------*/


    /*------------------ADD-------------------*/
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newEmployee(Model model){
        Employee employee=new Employee();
        ArrayList<Department> departmentArraylist = (ArrayList<Department>) restService.departmentList();
        model.addAttribute("employee", employee);
        model.addAttribute("action","new");
        model.addAttribute("departments", departmentArraylist);

        return "employee-form";
    }
    /*-----------------------------------------*/


    /*------------------EDIT{ID}-------------------*/
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEmployee(Model model, @PathVariable("id") Long id){
       Employee employee= restService.getById(id);
       model.addAttribute("employee", employee);
       model.addAttribute("action","edit");
       model.addAttribute("departments", restService.departmentList());
       return "employee-form";
    }
    /*-----------------------------------------*/

    /*------------------EDIT-------------------*/
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute Employee employee){


        restService.edit(employee);
        return "redirect:/app/main";
    }
    /*-----------------------------------------*/

    /*-----------------DELETE------------------*/
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id)
    {
        restService.delete(id);
        return "redirect:/app/main";
    }
   /*-----------------------------------------*/



}
