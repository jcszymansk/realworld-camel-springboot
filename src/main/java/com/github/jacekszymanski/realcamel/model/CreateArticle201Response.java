package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * CreateArticle201Response
 */

@JsonTypeName("CreateArticle_201_response")
@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class CreateArticle201Response {

  @JsonProperty("article")
  private Article article;

  public CreateArticle201Response article(Article article) {
    this.article = article;
    return this;
  }

  /**
   * Get article
   * @return article
  */
  @NotNull @Valid
  @Schema(name = "article", required = true)
  public Article getArticle() {
    return article;
  }

  public void setArticle(Article article) {
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
    CreateArticle201Response createArticle201Response = (CreateArticle201Response) o;
    return Objects.equals(this.article, createArticle201Response.article);
  }

  @Override
  public int hashCode() {
    return Objects.hash(article);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticle201Response {\n");
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
