package com.sap.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@SpringBootApplication
@EnableWebFlux
public class ChatbotStarterService {
  public static void main(String[] args) {
    SpringApplication.run(ChatbotStarterService.class);
  }
}
