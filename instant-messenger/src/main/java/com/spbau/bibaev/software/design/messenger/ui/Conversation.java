package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Conversation {
  private final User myUser;
  private final List<String> myMessages;
  @Override
  public String toString() {
    return myUser.getName();
  }

  public Conversation(@NotNull User user, @NotNull List<String> messages) {
    myUser = user;
    myMessages = messages;
  }

  public String getUserName() {
    return myUser.getName();
  }

  public User getUser() {
    return myUser;
  }
}
