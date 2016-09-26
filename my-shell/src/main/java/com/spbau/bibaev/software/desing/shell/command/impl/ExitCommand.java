package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

/**
 * Close the REPL command.
 * <p>If called in pipeline, then next command wont evaluated</p>
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class ExitCommand extends CommandBase {

  /**
   * Constructs a new exit command
   *
   * @param name The name of command - "exit"
   * @param args The list of arguments
   */
  public ExitCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    return ExecutionResult.SHUTDOWN;
  }
}
