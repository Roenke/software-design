package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Something, that can be executed
 */
public interface Executable {
  @NotNull
  ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException;
}
