package com.spbau.bibaev.software.desing.shell.command;

import org.jetbrains.annotations.NotNull;

public class CommandArg {
  private final String myValue;

  public CommandArg(@NotNull String value) {
    myValue = value;
  }

  @NotNull
  public String getValue() {
    return myValue;
  }
}
