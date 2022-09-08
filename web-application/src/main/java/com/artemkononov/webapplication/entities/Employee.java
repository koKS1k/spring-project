package com.artemkononov.webapplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Getter
@Setter

public class Employee {

    private Long id;
    private Department department;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Yekaterinburg")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer age;
    private Long salary;


    public Employee(Department department, String name, LocalDate date, long salary) {

        this.department = department;
        this.name = name;
        this.date = date;
        this.salary = salary;
        this.age=Period.between(date,LocalDate.now()).getYears();
    }


}
