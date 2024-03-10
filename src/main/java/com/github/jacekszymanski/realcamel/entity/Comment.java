package com.github.jacekszymanski.realcamel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString(callSuper = true, exclude = { "body", "article" })
@EqualsAndHashCode(callSuper = true, exclude = { "body", "article" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Base {

  @Column(nullable = false)
  @Lob
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
