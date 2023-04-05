package com.github.jacekszymanski.realcamel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

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
  private String title;

  @Column(nullable = false)
  private String body;

  @Column(nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author", nullable = false)
  private User author;

  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @Column(nullable = false)
  private Date updatedAt;

  @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
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
