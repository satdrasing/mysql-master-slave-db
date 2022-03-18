package com.satendra.mysqlmasterslave.mytest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.nest;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class EmployeeHandlerRoutes {

    @Bean
    public RouterFunction<ServerResponse> employeeRoutes(EmployeeController employeeController){
        return
                nest(path("/employees"),
                    route(GET("/{id}"), employeeController::getEmployee)
                            .andRoute(POST("/add"), employeeController::insertEmployee)
                );
    }
}
