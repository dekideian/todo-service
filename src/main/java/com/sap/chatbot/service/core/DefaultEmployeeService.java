package com.sap.chatbot.service.core;

import com.sap.chatbot.async.AsyncRunner;
import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.repository.api.sync.EmployeeRepository;
import com.sap.chatbot.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Service
public class DefaultEmployeeService implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final AsyncRunner asyncRunner;

  @Autowired
  public DefaultEmployeeService(EmployeeRepository employeeRepository, AsyncRunner asyncRunner) {
    this.employeeRepository = employeeRepository;
    this.asyncRunner = asyncRunner;
  }

  @Override
  public Mono<Employee> findOne(UUID id) {
    return asyncRunner.oneOrZero(() -> employeeRepository.findById(id));
  }

  @Override
  @Transactional
  public Flux<Employee> findAll() {
    return asyncRunner.many(employeeRepository::findAll);
  }
}

// TODO::
//    return asyncRunner.many(() -> transactionTemplate.execute((status) -> {
////                employeeRepository.findEmployeesOlderThan(1000L).forEach(System.out::println);
//          System.out.println("finding");
//          employeeRepository.findAll().forEach(System.out::println);
//          System.out.println("deleting");
//          employeeRepository.deleteAll();
//          System.out.println("finding again");
//          employeeRepository.findAll().forEach(System.out::println);
//          status.setRollbackOnly();
//          return Collections.emptyList();
//      }));
