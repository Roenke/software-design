package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

/**
 * @author Vitaliy.Bibaev
 */
public interface NamedUser extends User {
  @NotNull
  String getName();
}
