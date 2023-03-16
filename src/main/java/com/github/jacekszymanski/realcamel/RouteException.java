package com.github.jacekszymanski.realcamel;

public class RouteException extends RuntimeException {

  private static final int DEFAULT_REASON = 422;

  private final int reason;

  public RouteException() {
    reason = DEFAULT_REASON;
  }

  public RouteException(String message) {
    super(message);

    reason = DEFAULT_REASON;
  }

  public RouteException(String message, Throwable cause) {
    super(message, cause);

    reason = DEFAULT_REASON;
  }

  public RouteException(Throwable cause) {
    super(cause);

    reason = DEFAULT_REASON;
  }

  public RouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);

    reason = DEFAULT_REASON;
  }

  public RouteException(String message, int aReason) {
    super(message);

    reason = aReason;
  }

  public int getReason() {
    return reason;
  }
}
