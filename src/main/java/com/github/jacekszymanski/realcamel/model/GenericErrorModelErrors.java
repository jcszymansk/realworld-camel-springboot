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
 * GenericErrorModelErrors
 */

@JsonTypeName("GenericErrorModel_errors")
@Generated(value = "org.openapitools.codegen.languages.JavaCamelServerCodegen", date = "2023-01-24T20:39:29.911481+01:00[Europe/Prague]")
public class GenericErrorModelErrors {

  @JsonProperty("body")
  @Valid
  private List<String> body = new ArrayList<>();

  public GenericErrorModelErrors body(List<String> body) {
    this.body = body;
    return this;
  }

  public GenericErrorModelErrors addBodyItem(String bodyItem) {
    this.body.add(bodyItem);
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @NotNull
  @Schema(name = "body", required = true)
  public List<String> getBody() {
    return body;
  }

  public void setBody(List<String> body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenericErrorModelErrors genericErrorModelErrors = (GenericErrorModelErrors) o;
    return Objects.equals(this.body, genericErrorModelErrors.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GenericErrorModelErrors {\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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
