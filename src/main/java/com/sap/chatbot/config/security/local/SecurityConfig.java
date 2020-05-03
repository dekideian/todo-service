package com.sap.chatbot.config.security.local;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
@Profile({"dev", "test"})
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
    ServerHttpSecurity http) {
    return http
      .formLogin().disable()
      .httpBasic().disable()
      .csrf().disable()
      .logout().disable()
      .authorizeExchange()
      .anyExchange().permitAll().and()
      .build();
  }
}