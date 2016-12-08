package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.Objects;

public class UserImpl implements NamedUser {
  private final String myName;
  private final InetAddress myAddress;
  private final int myPort;

  public UserImpl(@NotNull String name, InetAddress address, int port) {
    myName = name;
    myAddress = address;
    myPort = port;
  }

  @NotNull
  public String getName() {
    return myName;
  }

  @NotNull
  public InetAddress getAddress() {
    return myAddress;
  }

  public int getPort() {
    return myPort;
  }

  @Override
  public int hashCode() {
    return Objects.hash(myAddress, myPort);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      final User user = (NamedUser) obj;
      return myAddress.equals(user.getAddress()) && myPort == user.getPort();
    }

    return false;
  }
}
