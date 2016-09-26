package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Something, that can be executed
 *
 * @author Vitaliy.Bibaev
 * @see ExecutionResult
 * @see CommandBase
 * @see InputStream
 * @see OutputStream
 */
public interface Executable {
  /**
   * Methods, that should implement the command logic
   *
   * @param in  Input Stream
   * @param out Output Stream
   * @param err Error Stream
   * @return Result of command execution
   * @throws IOException Thrown if IO operations with streams failed
   */
  @NotNull
  ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException;
}
