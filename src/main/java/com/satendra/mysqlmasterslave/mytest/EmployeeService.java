package com.satendra.mysqlmasterslave.mytest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true) // read from slave
    public EmployeeData getEmployee(Long id){
        return employeeRepository.findById(id).map(EmployeeData::new).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional // wright to master
    public EmployeeEntity insertEmployee(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);

    }
}
