package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

/**
 * Data class, primitive implementation of {@link NamedUser}
 */
public class UserImpl implements NamedUser {
  private final String myName;
  private final InetAddress myAddress;
  private final int myPort;

  /**
   * Constructs a new instance of {@link UserImpl}
   *
   * @param name    A name of the user
   * @param address A tcp/ip address of the user
   * @param port    A 16-bit port number
   */
  public UserImpl(@NotNull String name, InetAddress address, int port) {
    myName = name;
    myAddress = address;
    myPort = port;
  }

  /**
   * @return A name of user
   */
  @NotNull
  public String getName() {
    return myName;
  }

  /**
   * @return A tcp/ip address of the user
   */
  @NotNull
  public InetAddress getAddress() {
    return myAddress;
  }

  /**
   * @return A 16-bit port number
   */
  public int getPort() {
    return myPort;
  }
}
