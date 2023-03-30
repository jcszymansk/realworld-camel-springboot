package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class VerifyLoginTest extends TestsBase {

  private static final String JPA_ENDPOINT_PATTERN = "jpa:com.github.jacekszymanski.realcamel.entity.User*";
  private static final String ENTRY_ENDPOINT = "direct:verifyLogin";

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
      exchange.getMessage().setBody(List.of(user));
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(body);
      exchange.getIn().setHeader("Authorization", "Token xxx");
    });

    Assertions.assertEquals(body, resultExchange.getIn().getBody());
    Assertions.assertEquals(user, resultExchange.getProperty("loggedInUser"));

  }

  @Test
  public void testVerifyLoginNoUser() throws Exception {
    final Object body = new Object();

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      exchange.getMessage().setBody(Collections.emptyList());
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(body);
      exchange.getIn().setHeader("Authorization", "Token xxx");
    });

    Assertions.assertEquals(401, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
  }

}
