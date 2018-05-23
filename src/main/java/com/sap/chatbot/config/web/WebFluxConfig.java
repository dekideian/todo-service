package com.sap.chatbot.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .registerModules(new Jdk8Module())
        .registerModules(new VavrModule())
        .setDateFormat(new ISO8601DateFormat());
  }

  @Bean
  public Jackson2JsonEncoder encoder() {
    return new Jackson2JsonEncoder(objectMapper());
  }

  @Bean
  public Jackson2JsonDecoder decoder() {
    return new Jackson2JsonDecoder(objectMapper());
  }

  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    configurer.defaultCodecs().jackson2JsonEncoder(encoder());
    configurer.defaultCodecs().jackson2JsonDecoder(decoder());
  }
}
