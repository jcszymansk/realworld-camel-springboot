package com.github.jacekszymanski.realcamel.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
