package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.NonUniqueResultException;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class VerifyLoginTest extends TestsBase {

  private static final String JPA_ENDPOINT_PATTERN = "jpa:com.github.jacekszymanski.realcamel.entity.User*";
  private static final String ENTRY_ENDPOINT = "direct:verifyLogin";
  private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmNkZSJ9.iFBORfKYZ5YTir5cMVYMjEZ8sveV-Vg-lW6SShWkCLI";

  private static boolean adviced = false;

  private MockEndpoint mockEndpoint;

  @BeforeEach
  public void setUp() throws Exception {
    if (!adviced) {
      AdviceWith.adviceWith(camelContext, UriUtil.fromEndpointToRouteId(ENTRY_ENDPOINT),
          a -> {
            a.weaveByToUri(JPA_ENDPOINT_PATTERN).replace().to("mock:returnUser");
          });
      adviced = true;
    }

    mockEndpoint = camelContext.getEndpoint("mock:returnUser", MockEndpoint.class);
  }


  @Test
  public void testVerifyLoginOK() throws Exception {

    final Object body = new Object();

    final User user = UserUtil.defaultUserEntity();

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      exchange.setProperty("loggedInUser", user);
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(body);
      exchange.getIn().setHeader("Authorization", "Token " + TOKEN);
    });

    Assertions.assertEquals(body, resultExchange.getIn().getBody());
    Assertions.assertEquals(user, resultExchange.getProperty("loggedInUser"));

  }

  @Test
  public void testVerifyLoginNoUser() throws Exception {
    final Object body = new Object();

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      throw new NonUniqueResultException();
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(body);
      exchange.getIn().setHeader("Authorization", "Token " + TOKEN);
    });

    Assertions.assertEquals(401, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
  }

  @Test
  public void testVerifyLoginNoAuthHeader() throws Exception {
    final Object body = new Object();

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(body);
    });

    Assertions.assertEquals(401, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
  }

}
