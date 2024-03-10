package com.github.jacekszymanski.realcamel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "follows")
@Getter
@Setter
@EqualsAndHashCode
/*
 * This class is here only for Hibernate to create the table. I don't want it to ever
 * fetch any instances, esp. since there's so far no API method to get followers' list.
 *
 * I could use Hibernate's @Formula, but I decided I wanted to avoid any Hibernate
 * specific features and use only pure JPA.
 *
 * It also doesn't extend Base as it would only force me to declare another unique index
 * while as it is, Hibernate does exactly what it should.
 */
public class Follow implements Serializable {

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  @JoinColumn(name = "followed")
  private User followed;

  @Id
  @ManyToOne(fetch=FetchType.LAZY, optional = false)
  @JoinColumn(name = "follower")
  private User follower;

}
