package com.spbau.bibaev.software.desing.shell.command;

import com.spbau.bibaev.software.desing.shell.command.impl.*;
import com.spbau.bibaev.software.desing.shell.command.impl.todo.AssignCommand;
import com.spbau.bibaev.software.desing.shell.ex.CommandCreationException;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.StrongQuote;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandFactory {
  private final Class<DefaultCommand> DEFAULT_COMMAND_CLASS = DefaultCommand.class;
  private final Map<String, Class<? extends CommandBase>> COMMANDS = new ConcurrentHashMap<>();

  private CommandFactory() {
  }

  private static class Holder {
    static final CommandFactory INSTANCE = new CommandFactory();
  }

  {
    COMMANDS.put("exit", ExitCommand.class);
    COMMANDS.put("cat", CatCommand.class);
    COMMANDS.put("pwd", PwdCommand.class);
    COMMANDS.put("wc", WcCommand.class);
    COMMANDS.put("echo", EchoCommand.class);
  }

  public static CommandFactory getInstance() {
    return Holder.INSTANCE;
  }

  @NotNull
  public Executable createCommand(@NotNull String name, @NotNull List<String> args) throws CommandCreationException {
    try {
      if (COMMANDS.containsKey(name)) {
        return COMMANDS.get(name).getConstructor(String.class, List.class).newInstance(name, args);
      }

      if (args.size() == 0 && AssignCommand.isAssignExpression(name)) {
        return AssignCommand.class.getConstructor(String.class, List.class).newInstance(name, args);
      }

      return DEFAULT_COMMAND_CLASS.getConstructor(String.class, List.class).newInstance(name, args);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new CommandCreationException("Cannot create command " + name, e);
    }
  }
}
