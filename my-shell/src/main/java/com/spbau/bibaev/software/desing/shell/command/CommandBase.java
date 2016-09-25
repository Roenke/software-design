package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CommandBase implements Executable {
  private final String myName;
  private final List<String> myArgs;

  protected CommandBase(@NotNull String name, @NotNull List<String> args) {
    myName = name;
    myArgs = args;
  }

  @NotNull
  protected String getName() {
    return myName;
  }

  @NotNull
  protected List<String> getArgs() {
    return myArgs;
  }
}
