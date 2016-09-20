package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CommandBase implements Executable {
  protected final String ourName;
  protected final List<String> ourArgs;

  protected CommandBase(@NotNull String name, @NotNull List<String> args) {
    ourName = name;
    ourArgs = args;
  }
}
