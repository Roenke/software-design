package com.spbau.bibaev.software.desing.shell.ex;

import org.jetbrains.annotations.NotNull;

public class CommandCreationException extends Exception{
  public CommandCreationException(@NotNull String name, @NotNull Throwable cause) {
    super("Cannot create command " + name, cause);
  }
}
