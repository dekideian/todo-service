package com.sap.chatbot;

import com.sap.chatbot.config.async.AsyncRunner;
import com.sap.chatbot.repository.api.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@SpringBootApplication
public class ChatbotStarterService {
  public static void main(String[] args) {
    final ApplicationContext context = SpringApplication.run(ChatbotStarterService.class);

    final EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
    final AsyncRunner jdbcPooledAsyncRunner = context.getBean(AsyncRunner.class);

    jdbcPooledAsyncRunner
        .computeManyAsync(() -> employeeRepository.findEmployeesOlderThan(1000L))
        .subscribe(System.out::println);
  }
}
