package com.sap.chatbot.web.endpoints;

import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

  @GetMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }

  // TODO:: validation + post + delete.
  @PostMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Employee> createOne(
      @RequestParam("name") String name, @RequestParam("age") Long age) {
    return employeeService.createOne(name, age);
  }
}
