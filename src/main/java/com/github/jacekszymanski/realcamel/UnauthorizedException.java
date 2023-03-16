package com.github.jacekszymanski.realcamel;

public class UnauthorizedException extends RouteException {
  private static final int REASON = 401;

  public UnauthorizedException(String message) {
    super(message, REASON);
  }
}
