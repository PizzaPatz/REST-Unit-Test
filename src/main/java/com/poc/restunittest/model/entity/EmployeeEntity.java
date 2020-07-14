package com.poc.restunittest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "tbl_employee")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "department")
    private String department;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

}
