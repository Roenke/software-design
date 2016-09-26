package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command that takes all arguments and write them to OutputStream
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class EchoCommand extends CommandBase {

  /**
   * Constructs a new command
   *
   * @param name The name of command
   * @param args The list of arguments
   */
  public EchoCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    final String output = getArgs().stream().collect(Collectors.joining(" "));
    out.write(output.getBytes());
    out.write(System.lineSeparator().getBytes());
    return ExecutionResult.CONTINUE;
  }
}
