package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * NewArticle
 */

@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class NewArticle {

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("body")
  private String body;

  @JsonProperty("tagList")
  @Valid
  private List<String> tagList = null;

  public NewArticle title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull
  @Schema(name = "title", required = true)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public NewArticle description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @NotNull
  @Schema(name = "description", required = true)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NewArticle body(String body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @NotNull
  @Schema(name = "body", required = true)
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public NewArticle tagList(List<String> tagList) {
    this.tagList = tagList;
    return this;
  }

  public NewArticle addTagListItem(String tagListItem) {
    if (this.tagList == null) {
      this.tagList = new ArrayList<>();
    }
    this.tagList.add(tagListItem);
    return this;
  }

  /**
   * Get tagList
   * @return tagList
  */

  @Schema(name = "tagList", required = false)
  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewArticle newArticle = (NewArticle) o;
    return Objects.equals(this.title, newArticle.title) &&
        Objects.equals(this.description, newArticle.description) &&
        Objects.equals(this.body, newArticle.body) &&
        Objects.equals(this.tagList, newArticle.tagList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, body, tagList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewArticle {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    tagList: ").append(toIndentedString(tagList)).append("\n");
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
