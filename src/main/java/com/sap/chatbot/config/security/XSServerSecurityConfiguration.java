package com.sap.chatbot.config.security;

import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import com.sap.chatbot.config.security.cloud.ReactiveAuthorizations;

public class XSServerSecurityConfiguration {
	
	private XSServerSecurityConfiguration(){
		 throw new IllegalStateException("Utility class");
	}

  public static ServerHttpSecurity configure(ServerHttpSecurity http, String issuer) {
    return http
      .formLogin().disable()
      .httpBasic().disable()
      .csrf().disable()
      .logout().disable()
      .oauth2ResourceServer().jwt().jwtDecoder(reactiveJwtDecoder(issuer))
      .and()
      .and();
  }

  private static ReactiveJwtDecoder reactiveJwtDecoder(String issuer) {
    return new NimbusReactiveJwtDecoder(OpenIdConfigurationResolver.resolve(issuer).getJWKSetURI().toString());
  }

  public static ReactiveAuthorizations reactiveAuthorizations(String appName) {
    return new ReactiveAuthorizations(appName);
  }
}