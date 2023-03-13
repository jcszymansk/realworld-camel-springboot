package com.github.jacekszymanski.realcamel.util;

import org.apache.camel.Exchange;

import java.util.LinkedList;
import java.util.List;

public class ExceptionUtil {

  private ExceptionUtil() {}

  public static Exception exception(Exchange anExchange) {
    final Exception exc = anExchange.getException();

    if (exc != null) {
      return exc;
    }

    return anExchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
  }

  public static String[] messages(Throwable anException) {
    final List<String> messages = new LinkedList<>();

    for (Throwable exc = anException; exc != null; exc = exc.getCause()) {
      messages.add(exc.getLocalizedMessage());
    }

    return messages.toArray(new String[0]);
  }
}
