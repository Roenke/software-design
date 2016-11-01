package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

public class User {
  private final String myName;
  private final String myHost;
  private final int myPort;

  public User(@NotNull String name, @NotNull String host, int port) {
    myName = name;
    myHost = host;
    myPort = port;
  }

  public String getName() {
    return myName;
  }
  
  public String getHost() {
    return myHost;
  }

  public int getPort() {
    return myPort;
  }
}
