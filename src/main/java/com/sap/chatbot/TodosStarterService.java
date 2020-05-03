package com.sap.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TodosStarterService {
  public static void main(String[] args) {
    SpringApplication.run(TodosStarterService.class);
  }
}
