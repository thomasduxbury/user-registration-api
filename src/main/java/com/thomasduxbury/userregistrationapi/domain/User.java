package com.thomasduxbury.userregistrationapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Entity
@Table(name="users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  @NotBlank(message = "Email is mandatory")
  @Pattern(regexp = "(^$)|([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})")
  @Column(unique = true)
  private String email;

  @Pattern(regexp = "(^$)|^([A-Z][A-HJ-Y]?\\d[A-Z\\d]? ?\\d[A-Z]{2}|GIR ?0A{2})$")
  private String postcode;
  @NotBlank(message = "Password is mandatory")
  private String password;

  public User() {}

  public User(String name, String email, String postcode, String password) {
    this.name = name;
    this.email = email;
    this.postcode = postcode;
    this.password = password;
  }

  public long getId() { return id; }

  public void setId(long id) { this.id = id; }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return String.format("User{id=%s, name=%s, email=%s, postcode=%s, password=%s", id, name, email, postcode, password);
  }
}
