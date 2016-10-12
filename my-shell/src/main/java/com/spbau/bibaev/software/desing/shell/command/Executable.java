package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
   * @param in input Stream
   * @param out output Stream
   * @param err error Stream
   * @return result of command execution
   * @throws IOException thrown if IO operations with streams failed
   */
  @NotNull
  ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException;
}
