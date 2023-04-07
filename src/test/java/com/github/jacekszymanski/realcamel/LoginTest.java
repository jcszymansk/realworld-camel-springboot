package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.model.LoginRequest;
import com.github.jacekszymanski.realcamel.model.LoginUser;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class LoginTest extends TestsBase {
  private static final String JPA_ENDPOINT_PATTERN = "jpa:com.github.jacekszymanski.realcamel.entity.User*";
  private static final String ENTRY_ENDPOINT = "direct:login";
  private static final String LOGIN_USER_ENDPOINT = "direct:loginUser";

  /***
   * It's an ugly hack, but I can't advice the route more than once, and all crazy things seem to
   * happen to the context and mocks when the lifecycle is PER_CLASS and the advice in @BeforeAll.
   */
  private static boolean adviced = false;

  private MockEndpoint mockEndpoint;

  @BeforeEach
  public void setUp() throws Exception {
    if (!adviced) {
      AdviceWith.adviceWith(camelContext, UriUtil.fromEndpointToRouteId(ENTRY_ENDPOINT),
          a -> {
            a.weaveByToUri(JPA_ENDPOINT_PATTERN).replace().to("mock:returnUser");
            a.weaveByToUri(LOGIN_USER_ENDPOINT).remove();
          });
      adviced = true;
    }

    mockEndpoint = camelContext.getEndpoint("mock:returnUser", MockEndpoint.class);
  }

  @AfterEach
  public void tearDown() throws Exception {
    mockEndpoint.reset();
  }

  @Test
  public void testLoginOK() throws Exception {
    final LoginRequest loginRequest = loginRequest();
    final User user = UserUtil.defaultUserEntity();

    final MockEndpoint mockEndpoint = camelContext.getEndpoint("mock:returnUser", MockEndpoint.class);

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      System.err.println("replacing with");
      exchange.getMessage().setBody(List.of(user));
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(loginRequest);
    });

    Assertions.assertEquals(user, resultExchange.getMessage().getBody());
  }

  @Test
  public void testLoginBadPass() throws Exception {
    final LoginRequest loginRequest = loginRequest();
    final User user = UserUtil.defaultUserEntity();

    user.setPassword("1234");

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      exchange.getIn().setBody(List.of(user));
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(loginRequest);
    });

    Assertions.assertEquals(401, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
  }

  @Test
  public void testLoginNoUser() throws Exception {
    final LoginRequest loginRequest = loginRequest();

    mockEndpoint.whenAnyExchangeReceived(exchange -> {
      exchange.getIn().setBody(Collections.emptyList());
    });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(loginRequest);
    });

    Assertions.assertEquals(401, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
  }


  private LoginRequest loginRequest() {
    final LoginUser loginUser = new LoginUser();

    loginUser.setEmail(UserUtil.DEFAULT_EMAIL);
    loginUser.setPassword(UserUtil.DEFAULT_PASSWORD);

    final LoginRequest loginRequest = new LoginRequest();

    loginRequest.setUser(loginUser);

    return loginRequest;
  }

}
