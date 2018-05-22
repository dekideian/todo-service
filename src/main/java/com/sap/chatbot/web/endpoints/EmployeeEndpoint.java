package com.sap.chatbot.web.endpoints;

import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
@RestController
public class EmployeeEndpoint {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeEndpoint(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(path = "/employees")
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }

  //TODO:: validation + post + delete.

}
