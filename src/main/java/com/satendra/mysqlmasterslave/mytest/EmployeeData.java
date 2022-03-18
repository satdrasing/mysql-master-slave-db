package com.satendra.mysqlmasterslave.mytest;

import lombok.Data;

@Data
public class EmployeeData {

    public Long id;

    public String empName;

    public EmployeeData(EmployeeEntity employeeEntity) {
        id = employeeEntity.getId();
        empName = employeeEntity.getEmpName();
    }
}
