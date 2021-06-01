package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<>();
  }

  public Users(Collection<UserData> contacts) {
    this.delegate = new HashSet<>(contacts);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }

  public Users withAdded(UserData contact) {
    Users users = new Users(this);
    users.add(contact);
    return users;
  }
  public Users without(UserData contact) {
    Users users = new Users(this);
    users.remove(contact);
    return users;
  }
}
