package com.github.jacekszymanski.realcamel.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@EqualsAndHashCode
public class Favorite implements Serializable {

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  @JoinColumn(name = "article")
  private Article article;

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  @JoinColumn(name = "userid")
  private User user;

}
