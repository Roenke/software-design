package com.spbau.bibaev.software.design.shell.parsing;

import com.spbau.bibaev.software.design.shell.command.CommandArg;
import com.spbau.bibaev.software.design.shell.command.CommandFactory;
import com.spbau.bibaev.software.design.shell.command.Executable;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import com.spbau.bibaev.software.design.shell.ex.CommandCreationException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper wrapper for each command, substitute all quotes before command will be executed
 */
class ArgumentSubstitutionWrapper implements Executable {
  private final CommandArg myName;
  private final List<CommandArg> myArgs;

  ArgumentSubstitutionWrapper(@NotNull CommandArg name, @NotNull List<CommandArg> args) {
    myName = name;
    myArgs = args;
  }

  @Override
  public @NotNull ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out,
                                          @NotNull OutputStream err) throws IOException {
    String name = myName.getValue();
    List<String> args = myArgs.stream().map(CommandArg::getValue).collect(Collectors.toList());
    try {
      Executable command = CommandFactory.getInstance().createCommand(name, args);
      return command.execute(in, out, err);
    } catch (CommandCreationException e) {
      return ExecutionResult.SHUTDOWN;
    }
  }
}
