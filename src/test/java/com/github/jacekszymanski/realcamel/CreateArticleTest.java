package com.github.jacekszymanski.realcamel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.jacekszymanski.realcamel.model.CreateArticleRequest;
import com.github.jacekszymanski.realcamel.testutil.ArticleUtil;
import com.github.jacekszymanski.realcamel.testutil.UriUtil;
import com.github.jacekszymanski.realcamel.testutil.UserUtil;

@CamelSpringBootTest
@SpringBootTest(classes = CamelApplication.class)
public class CreateArticleTest extends TestsBase {

  public static final String ENTRY_ENDPOINT = "direct:createArticle";
  public static final String VERIFY_LOGIN_ENDPOINT = "direct:verifyLogin";
  public static final String SAVE_ENDPOINT = "jpa:com.github.jacekszymanski.realcamel.entity.Article*";

  @Test
  public void testCreateArticle() throws Exception {
    final CreateArticleRequest req = ArticleUtil.defaultCreateRequest();

    final String mockVerifyLoginEndpointUri = UriUtil.toMockUri(VERIFY_LOGIN_ENDPOINT);

    final MockEndpoint mockVerifyLoginEndpoint =
        camelContext.getEndpoint(mockVerifyLoginEndpointUri, MockEndpoint.class);

    AdviceWith.adviceWith(camelContext, UriUtil.fromEndpointToRouteId(ENTRY_ENDPOINT),
        a -> {
          a.weaveByToUri(VERIFY_LOGIN_ENDPOINT).replace().to(mockVerifyLoginEndpoint);
          a.weaveByToUri(SAVE_ENDPOINT).remove();
        });

    mockVerifyLoginEndpoint.whenAnyExchangeReceived(e -> {
      e.setProperty("loggedInUser", UserUtil.defaultUserEntity());
    });

    final Exchange result = producerTemplate.request(ENTRY_ENDPOINT, e -> {
      e.getIn().setBody(req);
    });

    Assertions.assertEquals(201, result.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE));



  }

}
