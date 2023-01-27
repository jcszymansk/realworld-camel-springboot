package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Article
 */

@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class Article {

  @JsonProperty("slug")
  private String slug;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("body")
  private String body;

  @JsonProperty("tagList")
  @Valid
  private List<String> tagList = new ArrayList<>();

  @JsonProperty("createdAt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdAt;

  @JsonProperty("updatedAt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date updatedAt;

  @JsonProperty("favorited")
  private Boolean favorited;

  @JsonProperty("favoritesCount")
  private Integer favoritesCount;

  @JsonProperty("author")
  private Profile author;

  public Article slug(String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Get slug
   * @return slug
  */
  @NotNull
  @Schema(name = "slug", required = true)
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public Article title(String title) {
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

  public Article description(String description) {
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

  public Article body(String body) {
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

  public Article tagList(List<String> tagList) {
    this.tagList = tagList;
    return this;
  }

  public Article addTagListItem(String tagListItem) {
    this.tagList.add(tagListItem);
    return this;
  }

  /**
   * Get tagList
   * @return tagList
  */
  @NotNull
  @Schema(name = "tagList", required = true)
  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }

  public Article createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @NotNull @Valid
  @Schema(name = "createdAt", required = true)
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Article updatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @NotNull @Valid
  @Schema(name = "updatedAt", required = true)
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Article favorited(Boolean favorited) {
    this.favorited = favorited;
    return this;
  }

  /**
   * Get favorited
   * @return favorited
  */
  @NotNull
  @Schema(name = "favorited", required = true)
  public Boolean getFavorited() {
    return favorited;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public Article favoritesCount(Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
    return this;
  }

  /**
   * Get favoritesCount
   * @return favoritesCount
  */
  @NotNull
  @Schema(name = "favoritesCount", required = true)
  public Integer getFavoritesCount() {
    return favoritesCount;
  }

  public void setFavoritesCount(Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
  }

  public Article author(Profile author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  */
  @NotNull @Valid
  @Schema(name = "author", required = true)
  public Profile getAuthor() {
    return author;
  }

  public void setAuthor(Profile author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return Objects.equals(this.slug, article.slug) &&
        Objects.equals(this.title, article.title) &&
        Objects.equals(this.description, article.description) &&
        Objects.equals(this.body, article.body) &&
        Objects.equals(this.tagList, article.tagList) &&
        Objects.equals(this.createdAt, article.createdAt) &&
        Objects.equals(this.updatedAt, article.updatedAt) &&
        Objects.equals(this.favorited, article.favorited) &&
        Objects.equals(this.favoritesCount, article.favoritesCount) &&
        Objects.equals(this.author, article.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(slug, title, description, body, tagList, createdAt, updatedAt, favorited, favoritesCount, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    tagList: ").append(toIndentedString(tagList)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    favorited: ").append(toIndentedString(favorited)).append("\n");
    sb.append("    favoritesCount: ").append(toIndentedString(favoritesCount)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
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
