package com.github.jacekszymanski.realcamel.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "selectByEmail", query = "select u from User u where u.email = :email"),
    @NamedQuery(name = "selectByUsername", query = "select u from User u where u.username = :username")
})
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "image")
public class User extends Base {
  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String bio;

  @Column
  @Lob
  private byte[] image;

}
