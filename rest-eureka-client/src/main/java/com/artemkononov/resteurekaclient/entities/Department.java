package com.artemkononov.resteurekaclient.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement
@Entity
public class Department  implements Serializable
{
    @Id
    @SequenceGenerator(name="department_sequence", sequenceName="department_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;

    public Department(String name)
    {
        this.name = name;
    }

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;


}