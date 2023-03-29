package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.model.Login200Response;
import com.github.jacekszymanski.realcamel.model.LoginRequest;
import com.github.jacekszymanski.realcamel.model.LoginUser;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class LoginTest extends TestsBase {
  private static final String JPA_ENDPOINT_PATTERN = "jpa:com.github.jacekszymanski.realcamel.entity.User*";
  private static final String ENTRY_ENDPOINT = "direct:login";
  private static final String LOGIN_USER_ENDPOINT = "direct:loginUser";

  @Test
  public void testLoginOK() throws Exception {
    final LoginRequest loginRequest = loginRequest();
    final User user = UserUtil.defaultUserEntity();

    AdviceWith.adviceWith(camelContext, UriUtil.fromEndpointToRouteId(ENTRY_ENDPOINT),
        a -> {
          a.weaveByToUri(JPA_ENDPOINT_PATTERN).replace().setBody(e -> List.of(user));
          a.weaveByToUri(LOGIN_USER_ENDPOINT).remove();
        });

    final Exchange resultExchange = producerTemplate.send(ENTRY_ENDPOINT, exchange -> {
      exchange.getIn().setBody(loginRequest);
    });

    Assertions.assertEquals(user, resultExchange.getIn().getBody());
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
