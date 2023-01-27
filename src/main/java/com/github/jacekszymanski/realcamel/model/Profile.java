package com.github.jacekszymanski.realcamel.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Profile
 */

@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class Profile {

  @JsonProperty("username")
  private String username;

  @JsonProperty("bio")
  private String bio;

  @JsonProperty("image")
  private String image;

  @JsonProperty("following")
  private Boolean following;

  public Profile username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @NotNull
  @Schema(name = "username", required = true)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Profile bio(String bio) {
    this.bio = bio;
    return this;
  }

  /**
   * Get bio
   * @return bio
  */
  @NotNull
  @Schema(name = "bio", required = true)
  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Profile image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  @NotNull
  @Schema(name = "image", required = true)
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Profile following(Boolean following) {
    this.following = following;
    return this;
  }

  /**
   * Get following
   * @return following
  */
  @NotNull
  @Schema(name = "following", required = true)
  public Boolean getFollowing() {
    return following;
  }

  public void setFollowing(Boolean following) {
    this.following = following;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(this.username, profile.username) &&
        Objects.equals(this.bio, profile.bio) &&
        Objects.equals(this.image, profile.image) &&
        Objects.equals(this.following, profile.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, bio, image, following);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    bio: ").append(toIndentedString(bio)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    following: ").append(toIndentedString(following)).append("\n");
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
