package com.github.jacekszymanski.realcamel;

import com.github.jacekszymanski.realcamel.model.CreateUserRequest;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class SaveUserTest {

  @Autowired
  private CamelContext camelContext;

  @Autowired
  private ProducerTemplate producerTemplate;

  private static final String MOCK_SAVE_ENDPOINT = "mock:save";

  @Test
  public void testNewUsernameTaken() throws Exception {
    final CreateUserRequest req = UserUtil.defaultUserRequest();

    final MockEndpoint mockJpaEndpoint = camelContext.getEndpoint(MOCK_SAVE_ENDPOINT, MockEndpoint.class);

    AdviceWith.adviceWith(camelContext, "direct_saveUser",
        a -> {
          a.weaveByToUri("jpa:com.github.jacekszymanski.realcamel.entity.User").replace().to(MOCK_SAVE_ENDPOINT);
        });

    mockJpaEndpoint.whenAnyExchangeReceived(new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        throw new ConstraintViolationException("unique index violated", new SQLException(), "unique");
      }
    });

    final Exchange resultExchange = producerTemplate.send("direct:saveUser",
        exchange -> exchange.getIn().setBody(req));

    Assertions.assertEquals(422, resultExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));

  }

}
