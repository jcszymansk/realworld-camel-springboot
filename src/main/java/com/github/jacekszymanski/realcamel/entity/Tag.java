package com.github.jacekszymanski.realcamel.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
