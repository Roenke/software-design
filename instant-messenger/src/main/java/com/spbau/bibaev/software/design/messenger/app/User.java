package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

public class User {
  private final String myName;
  private final InetAddress myAddress;

  public User(@NotNull String name, @NotNull InetAddress address) {
    myName = name;
    myAddress = address;
  }

  public String getName() {
    return myName;
  }

  public InetAddress getAddress() {
    return myAddress;
  }
}
