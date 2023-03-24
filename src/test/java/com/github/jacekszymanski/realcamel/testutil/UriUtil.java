package com.github.jacekszymanski.realcamel.testutil;

public class UriUtil {

  public static final String fromEndpointToRouteId(final String entryEndpointUri) {
    return entryEndpointUri.replace(':', '_');
  }

  public static final String toMockUri(final String endpointUri) {
    return endpointUri.replaceFirst("^.*:", "mock:");
  }

}
