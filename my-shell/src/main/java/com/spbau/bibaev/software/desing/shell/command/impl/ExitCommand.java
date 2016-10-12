package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
   * @param name the name of command - "exit"
   * @param args the list of arguments
   */
  public ExitCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    return ExecutionResult.SHUTDOWN;
  }
}
