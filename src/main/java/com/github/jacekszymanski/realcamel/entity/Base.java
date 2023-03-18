package com.github.jacekszymanski.realcamel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Base {

  @Id
  @GeneratedValue
  @JsonIgnore
  private Long id;

}
