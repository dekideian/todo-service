package com.sap.chatbot.config.security.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.sap.chatbot.config.security.XSServerSecurityConfiguration;

@EnableWebFluxSecurity
@Configuration
@Profile("cloud")
public class SecurityConfig {

  public static final String XSAPPNAME_PATTERN = "${vcap.services.history-uaa.credentials.xsappname}";
  public static final String ISSUER_URL_PATTERN = "${vcap.services.history-uaa.credentials.url}";

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
    ServerHttpSecurity http,
    @Value(ISSUER_URL_PATTERN) String issuer,
    @Value(XSAPPNAME_PATTERN) String appName
  ) {

    ReactiveAuthorizations reactiveAuthorizations = XSServerSecurityConfiguration.reactiveAuthorizations(appName);

    return XSServerSecurityConfiguration
      .configure(http, issuer)
      .authorizeExchange()
      .pathMatchers("/health/**/check").permitAll()
      .pathMatchers("/api/v1/files").access(reactiveAuthorizations.hasAnyScope("business-audit"))
      .pathMatchers("/api/v1/transcripts").access(reactiveAuthorizations.hasAnyScope("business-audit"))
      .pathMatchers("/api/v1/transcripts/byTenantAndUserId", "/api/v1/transcripts/byTenant")
        .access(reactiveAuthorizations.hasAnyScope("gdpr-cleanup"))
      .pathMatchers("/api/v1/transcripts/keys", "/api/v1/transcripts/keys/count")
        .access(reactiveAuthorizations.hasAnyScope("test-keys"))   
      .and()
      .build();
  }
}