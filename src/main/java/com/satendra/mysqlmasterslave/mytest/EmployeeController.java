package com.satendra.mysqlmasterslave.mytest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static java.lang.Long.valueOf;
import static java.net.URI.create;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class EmployeeController {

    final private EmployeeService employeeService;

    public ServerResponse getEmployee(ServerRequest request){
        return ok().body(employeeService.getEmployee(valueOf(request.pathVariable("id"))));
    }

    public ServerResponse insertEmployee(ServerRequest request) throws ServletException, IOException {
        var employeeEntity = employeeService.insertEmployee(request.body(EmployeeEntity.class));
        return created(create("/employees/" + employeeEntity.getId())).build();
    }
}
