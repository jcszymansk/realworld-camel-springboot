package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.model.CreateUserRequest;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class CreateUserTest extends TestsBase {

  public static final String SAVE_ENDPOINT = "direct:saveUser";
  public static final String LOGIN_ENDPOINT = "direct:loginUser";
  public static final String ENTRY_ENDPOINT = "direct:createUser";

  @Autowired
  private CamelContext camelContext;

  @Autowired
  private ProducerTemplate producerTemplate;

  @Test
  public void testCreateNewUser() throws Exception {
    final CreateUserRequest req = UserUtil.defaultUserRequest();

    final String mockSaveEndpoint = UriUtil.toMockUri(SAVE_ENDPOINT);
    final String mockLoginEndpoint = UriUtil.toMockUri(LOGIN_ENDPOINT);

    final MockEndpoint mockJpaEndpoint = camelContext.getEndpoint(mockSaveEndpoint, MockEndpoint.class);
    final MockEndpoint mockTokenEndpoint = camelContext.getEndpoint(mockLoginEndpoint, MockEndpoint.class);

    AdviceWith.adviceWith(camelContext, UriUtil.fromEndpointToRouteId(ENTRY_ENDPOINT),
        a -> {
          a.weaveByToUri(SAVE_ENDPOINT).replace().to(mockSaveEndpoint);
          a.weaveByToUri(LOGIN_ENDPOINT).replace().to(mockLoginEndpoint);
        });

    final User userEntity = UserUtil.defaultUserEntity();

    mockJpaEndpoint.expectedBodiesReceived(userEntity);
    mockTokenEndpoint.expectedBodiesReceived(userEntity);

    producerTemplate.sendBody(ENTRY_ENDPOINT, req);

    mockJpaEndpoint.assertIsSatisfied();
    mockTokenEndpoint.assertIsSatisfied();

  }

 }
