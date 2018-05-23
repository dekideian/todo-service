package com.sap.chatbot.service.api;

import com.sap.chatbot.domain.entities.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
public interface EmployeeService {
  Mono<Employee> createOne(String name, Long age);

  Mono<Employee> findOne(UUID id);

  Flux<Employee> findAll();
}
