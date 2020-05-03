package com.sap.chatbot.config.security;

import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import com.sap.chatbot.exception.OpenIdParseException;

import org.springframework.web.client.RestTemplate;

/*
* Responsible for retrieving standard openid configuration provided by the issuer (in our case by xsuaa, via the credentials.url field injected in VCAP_SERVICES),
* and NOT validating the issuer.
* This configuration is used in the JWT validation algorithm for e.g.
* */
public class OpenIdConfigurationResolver {
	
	private OpenIdConfigurationResolver() {
		throw new IllegalStateException("Utility class");
	}

  public static OIDCProviderMetadata resolve(String xsuaaUrl) {
    return parse(getOpenidConfiguration(xsuaaUrl));
  }

  private static String getOpenidConfiguration(String issuer) {
    RestTemplate rest = new RestTemplate();
    try {
      return rest.getForObject(issuer + "/.well-known/openid-configuration", String.class);
    } catch(RuntimeException e) {
      throw new IllegalArgumentException("Unable to resolve the OpenID Configuration with the provided Issuer of \"" + issuer + "\"", e);
    }
  }

  private static OIDCProviderMetadata parse(String body) {
    try {
      return OIDCProviderMetadata.parse(body);
    }
    catch (ParseException e) {
      throw new OpenIdParseException(e);
    }
  }
}