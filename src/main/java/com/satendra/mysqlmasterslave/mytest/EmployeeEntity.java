package com.satendra.mysqlmasterslave.mytest;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "emp_name")
    public String empName;


}
