package com.github.jacekszymanski.realcamel.entity;

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
  private Long id;

}
