package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class EchoCommand extends CommandBase {
  public EchoCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    final String output = ourArgs.stream().map(CommandArg::getValue).collect(Collectors.joining(" "));
    out.write(output.getBytes());
    out.write(System.lineSeparator().getBytes());
    return ExecutionResult.CONTINUE;
  }
}
