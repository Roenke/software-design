package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.util.IoStreamUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Command for delegating user command to OS, if command not matched with any of already existed
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class DefaultCommand extends CommandBase {

  /**
   * Constructs a new command.
   *
   * @param name the name of which will executed
   * @param args the list of parameters which will passed to command
   */
  public DefaultCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    List<String> command = new ArrayList<>(getArgs());
    command.add(0, getName());
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.redirectError(ProcessBuilder.Redirect.PIPE)
        .redirectInput(ProcessBuilder.Redirect.PIPE)
        .redirectOutput(ProcessBuilder.Redirect.PIPE);

    final Process start = pb.start();
    InputStream inputStream = start.getInputStream();
    IoStreamUtils.copy(inputStream, out);

    return ExecutionResult.CONTINUE;
  }
}
