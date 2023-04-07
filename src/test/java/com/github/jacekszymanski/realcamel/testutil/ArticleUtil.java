package com.github.jacekszymanski.realcamel.testutil;

import java.util.List;

import com.github.jacekszymanski.realcamel.model.CreateArticleRequest;
import com.github.jacekszymanski.realcamel.model.NewArticle;

public class ArticleUtil {

  public static final String ARTICLE_TITLE = "Article title";
  public static final String ARTICLE_BODY = "Article body";
  public static final String ARTICLE_DESCRIPTION = "Article description";
  public static final List<String> ARTICLE_TAGS = List.of("tag1", "tag2", "tag3");

  public static CreateArticleRequest defaultCreateRequest() {
    final NewArticle article = new NewArticle()
      .title(ARTICLE_TITLE)
      .body(ARTICLE_BODY)
      .description(ARTICLE_DESCRIPTION)
      .tagList(ARTICLE_TAGS);

    final CreateArticleRequest req = new CreateArticleRequest().article(article);

    return req;
  }
}
