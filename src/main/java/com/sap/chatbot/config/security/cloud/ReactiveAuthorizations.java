package com.sap.chatbot.config.security.cloud;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;

public class ReactiveAuthorizations {

  private static final Logger logger = LoggerFactory.getLogger(ReactiveAuthorizations.class);

  private static final String SEPARATOR = ".";
  private static final String SCOPE_PREFIX = "SCOPE_";

  private final String authorityPrefix;

  public ReactiveAuthorizations(String appName) {
    this.authorityPrefix = SCOPE_PREFIX + appName + SEPARATOR;
    logger.info("Scope validation for xsappname: {}", appName);
  }

  public ReactiveAuthorizationManager<AuthorizationContext> hasAnyScope(String... scopes) {
    List<String> myScopes = Arrays.asList(scopes);
    logger.info("Scopes to validate against: {}", String.join(",", myScopes));


    return (manager, object) -> manager
      .map(authentication -> {
        logger.info("Token scopes: {}",
          String.join(
            ", ",
            authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
        return authentication
        .getAuthorities().stream()
        .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith(SCOPE_PREFIX))
        .anyMatch(grantedAuthority -> isAuthorityInScopes(grantedAuthority, myScopes) && isValidApprover(grantedAuthority));
      })
      .map(AuthorizationDecision::new);
  }

  private boolean isValidApprover(GrantedAuthority grantedAuthority) {
    return grantedAuthority.getAuthority().startsWith(authorityPrefix);
  }

  private boolean isAuthorityInScopes(GrantedAuthority grantedAuthority,
    List<String> myScopes) {

    String scope = StringUtils.removeStart(grantedAuthority.getAuthority(), authorityPrefix);

    return myScopes.contains(scope);
  }


}