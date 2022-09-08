package com.artemkononov.resteurekaclient.entities;


import com.artemkononov.resteurekaclient.configurations.LocalDateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;


@NoArgsConstructor
@Getter
@Setter
@Entity
/*
    Проблема в том, что объекты загружаются лениво,
    а сериализация происходит до того, как они загружаются полностью.
    Славься StackOverFlow.
*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @SequenceGenerator(name="employee_sequence", sequenceName="employee_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private Department department;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Yekaterinburg")
    private LocalDate date;


    private Integer age;

    @Column(nullable = false)
    private Long salary;


    public Employee(Department department, String name, LocalDate date, long salary) {

        this.department = department;
        this.name = name;
        this.date = date;
        this.salary = salary;
        this.age=Period.between(date,LocalDate.now()).getYears();
    }


}
