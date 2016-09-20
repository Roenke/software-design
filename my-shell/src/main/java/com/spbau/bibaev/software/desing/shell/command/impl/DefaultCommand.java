package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

public class DefaultCommand extends CommandBase {
  public DefaultCommand(@NotNull List<String> args) {
    super(args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    return ExecutionResult.CONTINUE;
  }
}
