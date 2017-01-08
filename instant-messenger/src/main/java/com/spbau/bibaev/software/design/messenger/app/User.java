package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

/**
 * Common interface for an anonymous user
 *
 * @author Vitaliy.Bibaev
 */
public interface User {
  /**
   * Return TCP/IP address of this user
   *
   * @return An address of the user
   */
  @NotNull
  InetAddress getAddress();

  /**
   * Returns the port which listened by this user
   *
   * @return A 16-bit port number
   */
  int getPort();
}
