package com.github.jacekszymanski.realcamel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "tags")
@Getter
@Setter
@ToString(exclude = { "article" })
@EqualsAndHashCode(exclude = { "article" })
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

  @Id
  @Column(nullable = false)
  private String tag;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "article")
  @JsonIgnore
  private Article article;

}
