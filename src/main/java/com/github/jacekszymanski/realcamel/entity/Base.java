package com.github.jacekszymanski.realcamel.entity;

import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class Base {

  @Id
  @GeneratedValue
  @SequenceGenerator(sequenceName = "hibernate_sequence", name = "hibernate_sequence")
  private Long id;

}
