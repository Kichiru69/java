package ru.stqa.pft.mantis.model;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")

public class UserData {

  @Id
  private int id = Integer.MAX_VALUE;

  private String username;

  private String email;

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withFirstname(String firstname) {
    this.username = firstname;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }


  public String getUsername() {
    return username;
  }


  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }


  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", firstname='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

}
