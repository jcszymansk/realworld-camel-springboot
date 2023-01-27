package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * CreateArticleRequest
 */

@JsonTypeName("CreateArticle_request")
@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class CreateArticleRequest {

  @JsonProperty("article")
  private NewArticle article;

  public CreateArticleRequest article(NewArticle article) {
    this.article = article;
    return this;
  }

  /**
   * Get article
   * @return article
  */
  @NotNull @Valid
  @Schema(name = "article", required = true)
  public NewArticle getArticle() {
    return article;
  }

  public void setArticle(NewArticle article) {
    this.article = article;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArticleRequest createArticleRequest = (CreateArticleRequest) o;
    return Objects.equals(this.article, createArticleRequest.article);
  }

  @Override
  public int hashCode() {
    return Objects.hash(article);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticleRequest {\n");
    sb.append("    article: ").append(toIndentedString(article)).append("\n");
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
