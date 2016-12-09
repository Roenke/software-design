package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an user with name
 *
 * @author Vitaliy.Bibaev
 */
public interface NamedUser extends User {
  /**
   * Provide username
   *
   * @return A username
   */
  @NotNull
  String getName();
}
