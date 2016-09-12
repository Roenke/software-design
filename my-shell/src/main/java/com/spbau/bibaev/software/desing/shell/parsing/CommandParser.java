package com.spbau.bibaev.software.desing.shell.parsing;

import com.spbau.bibaev.software.desing.shell.Executable;
import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandFactory;
import com.spbau.bibaev.software.desing.shell.ex.CommandCreationException;
import com.spbau.bibaev.software.desing.shell.ex.EmptyCommandException;
import com.spbau.bibaev.software.desing.shell.pipe.PipeHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CommandParser {
  public static Executable parse(@NotNull String input) throws EmptyCommandException, CommandCreationException {
    List<String> commands = CommandListParser.parse(input);
    List<Executable> executables = new ArrayList<>();
    for(String command : commands) {
      final String[] words = command.trim().split("\\s+");
      if(words.length == 0) {
        throw new EmptyCommandException("Command must contain one or more characters");
      }
      List<CommandArg> args = Arrays.stream(words).map(CommandArg::new).collect(Collectors.toList());
      executables.add(CommandFactory.getInstance().createCommand(args));
    }

    Executable previous = executables.get(0);
    for(int i = 1; i < executables.size(); i++) {
      previous = new PipeHandler(previous, executables.get(i));
    }

    return previous;
  }
}
