package com.spbau.bibaev.software.desing.shell.command;


import com.spbau.bibaev.software.desing.shell.Executable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CommandBase implements Executable {
  protected final List<CommandArg> ourArgs;

  protected CommandBase(@NotNull List<CommandArg> args) {
    ourArgs = args;
  }
}
