package com.github.jacekszymanski.realcamel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.TemplatedRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class EmptyRoutes extends RouteBuilder {

  private static final List<String> STARTER_URIS = List.of(
      "direct:createArticle",
      "direct:deleteArticle",
      "direct:getArticle",
      "direct:getArticles",
      "direct:getArticlesFeed",
      "direct:updateArticle",
      "direct:createArticleComment",
      "direct:deleteArticleComment",
      "direct:getArticleComments",
      "direct:createArticleFavorite",
      "direct:deleteArticleFavorite",
      "direct:followUserByUsername",
      "direct:getProfileByUsername",
      "direct:unfollowUserByUsername",
      "direct:getTags",
      //"direct:createUser",
      "direct:getCurrentUser",
      "direct:login",
      "direct:updateCurrentUser"
  );

  @Override
  public void configure() throws Exception {

    routeTemplate("emptyRouteTemplate").
        templateParameter("startUri").
        from("{{startUri}}").
        log("{{startUri}} was called, body ${body} headers ${headers}");


    for (String starterUri : STARTER_URIS) {
      final String id = starterUri.replace(':', '_');

      templatedRoute("emptyRouteTemplate").
          routeId(id).
          parameter("startUri", starterUri).
          prefixId(id);
    }
  }

}
