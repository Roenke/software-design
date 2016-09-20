package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class CatCommand extends CommandBase {
  private static final int BUFFER_SIZE = 4096;

  public CatCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    if (ourArgs.size() > 1) {
      String filename = ourArgs.get(1).getValue();
      File file = new File(filename);
      if(!file.exists()) {
        err.write(String.format("File %s not found", filename).getBytes());
      }
      Files.copy(file.toPath(), out);
    }

    copyStream(in, out);
    return ExecutionResult.CONTINUE;
  }

  private void copyStream(@NotNull InputStream in, @NotNull OutputStream os) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int read = in.read(buffer);
    while(read > 0) {
      os.write(buffer);
    }
  }
}
