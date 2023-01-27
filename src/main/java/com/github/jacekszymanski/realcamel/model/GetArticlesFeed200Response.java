package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * GetArticlesFeed200Response
 */

@JsonTypeName("GetArticlesFeed_200_response")
@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class GetArticlesFeed200Response {

  @JsonProperty("articles")
  @Valid
  private List<Article> articles = new ArrayList<>();

  @JsonProperty("articlesCount")
  private Integer articlesCount;

  public GetArticlesFeed200Response articles(List<Article> articles) {
    this.articles = articles;
    return this;
  }

  public GetArticlesFeed200Response addArticlesItem(Article articlesItem) {
    this.articles.add(articlesItem);
    return this;
  }

  /**
   * Get articles
   * @return articles
  */
  @NotNull @Valid
  @Schema(name = "articles", required = true)
  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public GetArticlesFeed200Response articlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
    return this;
  }

  /**
   * Get articlesCount
   * @return articlesCount
  */
  @NotNull
  @Schema(name = "articlesCount", required = true)
  public Integer getArticlesCount() {
    return articlesCount;
  }

  public void setArticlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetArticlesFeed200Response getArticlesFeed200Response = (GetArticlesFeed200Response) o;
    return Objects.equals(this.articles, getArticlesFeed200Response.articles) &&
        Objects.equals(this.articlesCount, getArticlesFeed200Response.articlesCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articles, articlesCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetArticlesFeed200Response {\n");
    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    articlesCount: ").append(toIndentedString(articlesCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
