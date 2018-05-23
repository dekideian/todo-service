package com.sap.chatbot.web.controller;

import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.forms.EmployeeCreationForm;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
@RestController
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }

  @PostMapping(
    path = "/employee",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Mono<Employee> createOne(EmployeeCreationForm employeeCreationForm) {
    return employeeService.createOne(employeeCreationForm.getName(), employeeCreationForm.getAge());
  }
}
