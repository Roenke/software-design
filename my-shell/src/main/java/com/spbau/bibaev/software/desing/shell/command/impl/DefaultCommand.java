package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCommand extends CommandBase {
  private static byte[] BUFFER = new byte[4096];
  public DefaultCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    List<String> command = new ArrayList<>(getArgs());
    command.add(0, getName());
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectInput(ProcessBuilder.Redirect.PIPE)
        .redirectOutput(ProcessBuilder.Redirect.PIPE);

    final Process start = pb.start();
    InputStream inputStream = start.getInputStream();
    pipe(inputStream, out);

    return ExecutionResult.CONTINUE;
  }

  private static void pipe(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
    int readCount = inputStream.read(BUFFER);
    while(readCount != -1) {
      outputStream.write(BUFFER, 0, readCount);
      readCount = inputStream.read(BUFFER);
    }
  }
}
