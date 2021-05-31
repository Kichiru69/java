package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")

public class ContactData {

  @Id
  private int id = Integer.MAX_VALUE;

  @Type(type = "text")
  private String username;

  @Type(type = "text")
  private String email;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.username = firstname;
    return this;
  }

  public ContactData withEmail(String email) {
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
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

}
