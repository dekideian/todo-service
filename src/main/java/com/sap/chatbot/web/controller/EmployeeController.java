package com.sap.chatbot.web.controller;

import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.forms.EmployeeCreationForm;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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

  @GetMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }

  @PostMapping(path = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Employee> createOne(@Valid @RequestBody Mono<EmployeeCreationForm> requestBody) {
    return requestBody.flatMap(
        employeeCreationForm ->
            employeeService.createOne(
                employeeCreationForm.getName(), employeeCreationForm.getAge()));
  }

  @Bean
  public RouterFunction<ServerResponse> route() {
    return RouterFunctions.route(
        RequestPredicates.POST("/example")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        (request) ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(EmployeeCreationForm.class), EmployeeCreationForm.class));
  }
}
