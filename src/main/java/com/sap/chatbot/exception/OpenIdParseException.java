package com.sap.chatbot.exception;

@SuppressWarnings("serial")
public final class OpenIdParseException extends RuntimeException {

  public OpenIdParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public OpenIdParseException(String message) {
    super(message);
  }

  public OpenIdParseException(Throwable cause) {
    super(cause);
  }
}
