package com.github.jacekszymanski.realcamel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString(callSuper = true, exclude = { "body", "article" })
@EqualsAndHashCode(callSuper = true, exclude = { "body", "article" })
@Builder
public class Comment extends Base {

  @Column(nullable = false)
  private String body;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author", nullable = false)
  private User author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "article", nullable = false)
  @JsonIgnore
  private Article article;

  @Column(nullable = false)
  private Date createdAt;

  @PrePersist
  public void prepare() {
    createdAt = new Date();
  }

}
