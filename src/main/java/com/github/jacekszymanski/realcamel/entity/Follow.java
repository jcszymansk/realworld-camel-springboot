package com.github.jacekszymanski.realcamel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "follows")
@Getter
@Setter
@EqualsAndHashCode
public class Follow implements Serializable {

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  private User followed;

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  private User follower;

}
