package com.sap.chatbot.web.controller;

import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.forms.EmployeeCreationForm;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

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
  public Flux<Employee> findAll(
      @RequestParam(value = "olderThan", required = false) Optional<Long> maybeAge) {
    return maybeAge.map(employeeService::findAllOlderThan).orElseGet(employeeService::findAll);
  }

  @PostMapping(path = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Employee> createOne(@Valid @RequestBody Mono<EmployeeCreationForm> requestBody) {
    return createOneHandler(requestBody);
  }

  @Bean
  public RouterFunction<ServerResponse> createOneWebFlux() {
    return RouterFunctions.route(
        RequestPredicates.POST("/employee-webflux")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        (request) ->
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                    createOneHandler(request.bodyToMono(EmployeeCreationForm.class)),
                    Employee.class));
  }


  @Bean
  public RouterFunction<ServerResponse> findAllWebFlux (){
    return RouterFunctions.route(
        RequestPredicates.GET("/employee-webflux")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        (request) ->
          ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(getManyHandler(), Employee.class)
    );
  }


  private Flux<Employee> getManyHandler(){
    return employeeService.findAll();
  }

  private Mono<Employee> createOneHandler(Mono<EmployeeCreationForm> requestBody) {
    return requestBody.flatMap(
        employeeCreationForm ->
            employeeService.createOne(
                employeeCreationForm.getName(), employeeCreationForm.getAge()));
  }
}
