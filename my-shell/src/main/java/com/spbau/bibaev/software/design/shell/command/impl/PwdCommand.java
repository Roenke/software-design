package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.EnvironmentImpl;
import com.spbau.bibaev.software.design.shell.command.CommandBase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Flush to output stream the absolute path to current directory
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class PwdCommand extends CommandBase {

  /**
   * Constructs a new command
   *
   * @param name the name of command - "pwd"
   * @param args the list of arguments
   */
  public PwdCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    Path currentDirectory = EnvironmentImpl.getInstance().getCurrentDirectory();
    out.write(currentDirectory.toAbsolutePath().toString().getBytes());
    out.write(System.lineSeparator().getBytes());
    return ExecutionResult.CONTINUE;
  }
}
