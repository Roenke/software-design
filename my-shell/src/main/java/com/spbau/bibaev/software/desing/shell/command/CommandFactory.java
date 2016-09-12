package com.spbau.bibaev.software.desing.shell.command;

import com.spbau.bibaev.software.desing.shell.Executable;
import com.spbau.bibaev.software.desing.shell.command.impl.*;
import com.spbau.bibaev.software.desing.shell.command.impl.todo.AssignCommand;
import com.spbau.bibaev.software.desing.shell.ex.CommandCreationException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
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

  public Executable createCommand(@NotNull List<CommandArg> args)
      throws CommandCreationException {
    CommandArg name = args.get(0);
    if(args.size() == 1 && isAssignment(args.get(0).getValue())) {
      return new AssignCommand(args);
    }

    Class<? extends CommandBase> clazz = COMMANDS.getOrDefault(name.getValue(), DEFAULT_COMMAND_CLASS);
    final Constructor<? extends CommandBase> constructor;
    try {
      constructor = clazz.getConstructor(List.class);
      return constructor.newInstance(args);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      throw new CommandCreationException(name.getValue(), e);
    }
  }

  private boolean isAssignment(@NotNull String expr) {
    int assignIndex = expr.indexOf('=');
    if(assignIndex == -1) {
      return false;
    }

    for(int i = 0; i < assignIndex; i++) {
      if(!Character.isLetterOrDigit(expr.charAt(i))) {
        return false;
      }
    }

    return true;
  }
}
