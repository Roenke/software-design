package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

public class CatCommand extends CommandBase {
  private static final int BUFFER_SIZE = 4096;

  public CatCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    if (getArgs().size() > 1) {
      String filename = getArgs().get(0);
      File file = new File(filename);
      if (!file.exists()) {
        err.write(String.format("File %s not found", filename).getBytes());
      }
      Files.copy(file.toPath(), out);
    } else {
      copyStream(in, out);
    }

    return ExecutionResult.CONTINUE;
  }

  private void copyStream(@NotNull InputStream in, @NotNull OutputStream os) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int readCount = in.read(buffer);
    while (readCount > 0) {
      os.write(buffer);
      readCount = in.read(buffer);
    }
  }
}
