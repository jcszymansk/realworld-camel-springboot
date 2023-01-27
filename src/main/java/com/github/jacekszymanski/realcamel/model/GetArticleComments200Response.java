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
 * GetArticleComments200Response
 */

@JsonTypeName("GetArticleComments_200_response")
@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class GetArticleComments200Response {

  @JsonProperty("comments")
  @Valid
  private List<Comment> comments = new ArrayList<>();

  public GetArticleComments200Response comments(List<Comment> comments) {
    this.comments = comments;
    return this;
  }

  public GetArticleComments200Response addCommentsItem(Comment commentsItem) {
    this.comments.add(commentsItem);
    return this;
  }

  /**
   * Get comments
   * @return comments
  */
  @NotNull @Valid
  @Schema(name = "comments", required = true)
  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetArticleComments200Response getArticleComments200Response = (GetArticleComments200Response) o;
    return Objects.equals(this.comments, getArticleComments200Response.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetArticleComments200Response {\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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
