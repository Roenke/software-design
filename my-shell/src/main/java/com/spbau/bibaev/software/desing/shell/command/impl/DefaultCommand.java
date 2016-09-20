package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCommand extends CommandBase {
  public DefaultCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    List<String> command = new ArrayList<>(ourArgs);
    command.add(0, ourName);
    ProcessBuilder pb = new ProcessBuilder(command);

    pb.redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectInput(ProcessBuilder.Redirect.PIPE)
        .redirectOutput(ProcessBuilder.Redirect.PIPE);

    final Process start = pb.start();
    final int read = start.getInputStream().read();

    return ExecutionResult.CONTINUE;
  }
}
