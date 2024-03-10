package com.github.jacekszymanski.realcamel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "articles")
@Getter
@Setter
@ToString(callSuper = true, exclude = { "body", "description" })
@EqualsAndHashCode(callSuper = true)
public class Article extends Base {

  @Column(nullable = false)
  @Lob
  private String title;

  @Column(nullable = false)
  @Lob
  private String body;

  @Column(nullable = false)
  @Lob
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author", nullable = false)
  private User author;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
  private List<Tag> tagList = new ArrayList<>();

  @PrePersist
  @PreUpdate
  public void prepare() {
    final Date now = new Date();

    setUpdatedAt(now);
    setCreatedAt(now); // it will be ignored on update
  }

  public void addTag(String tag) {
    tagList.add(new Tag(tag, this));
  }

}
