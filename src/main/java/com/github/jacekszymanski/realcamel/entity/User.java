package com.github.jacekszymanski.realcamel.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "selectByEmail", query = "select u from User u where u.email = :email"),
    @NamedQuery(name = "selectByUsername", query = "select u from User u where u.username = :username")
})
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", bio='" + bio + '\'' +
        '}';
  }
}
