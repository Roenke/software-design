package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
   * @param name the name of command
   * @param args the list of arguments
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
