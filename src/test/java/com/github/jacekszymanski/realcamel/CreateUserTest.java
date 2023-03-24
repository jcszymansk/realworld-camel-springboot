package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.entity.User;
import com.github.jacekszymanski.realcamel.model.CreateUserRequest;
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
public class CreateUserTest {

  private static final String MOCK_SAVE_ENDPOINT = "mock:save";
  private static final String MOCK_TOKEN_ENDPOINT = "mock:token";

  @Autowired
  private CamelContext camelContext;

  @Autowired
  private ProducerTemplate producerTemplate;

  @Test
  public void testCreateNewUser() throws Exception {
    final CreateUserRequest req = UserUtil.defaultUserRequest();

    final MockEndpoint mockJpaEndpoint = camelContext.getEndpoint(MOCK_SAVE_ENDPOINT, MockEndpoint.class);
    final MockEndpoint mockTokenEndpoint = camelContext.getEndpoint(MOCK_TOKEN_ENDPOINT, MockEndpoint.class);

    AdviceWith.adviceWith(camelContext, "direct_createUser",
        a -> {
          a.weaveByToUri("direct:saveUser").replace().to(MOCK_SAVE_ENDPOINT);
          a.weaveByToUri("direct:loginUser").replace().to(MOCK_TOKEN_ENDPOINT);
        });

    final User userEntity = UserUtil.defaultUserEntity();

    mockJpaEndpoint.expectedBodiesReceived(userEntity);
    mockTokenEndpoint.expectedBodiesReceived(userEntity);

    producerTemplate.sendBody("direct:createUser", req);

    mockJpaEndpoint.assertIsSatisfied();
    mockTokenEndpoint.assertIsSatisfied();

  }

 }
