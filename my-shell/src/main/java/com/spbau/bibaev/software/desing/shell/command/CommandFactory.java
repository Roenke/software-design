package com.spbau.bibaev.software.desing.shell.command;

import com.spbau.bibaev.software.desing.shell.command.impl.*;
import com.spbau.bibaev.software.desing.shell.command.impl.AssignCommand;
import com.spbau.bibaev.software.desing.shell.ex.CommandCreationException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton object. Must be used for creating instances of subclasses of {@link CommandBase}
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 * @see DefaultCommand
 */
public class CommandFactory {
  /**
   * Default command for unmatched user queries
   */
  private final Class<DefaultCommand> DEFAULT_COMMAND_CLASS = DefaultCommand.class;

  /**
   * Map command name -> class of command
   * Put your command here
   */
  private final Map<String, Class<? extends CommandBase>> COMMANDS = new ConcurrentHashMap<>();

  private CommandFactory() {
  }

  private static class Holder {
    static final CommandFactory INSTANCE = new CommandFactory();
  }

  /*
    Put your command here
   */ {
    COMMANDS.put("exit", ExitCommand.class);
    COMMANDS.put("cat", CatCommand.class);
    COMMANDS.put("pwd", PwdCommand.class);
    COMMANDS.put("wc", WcCommand.class);
    COMMANDS.put("echo", EchoCommand.class);
  }

  /**
   * Returns instance of {@link CommandFactory}
   * Instance creation is "on demand"
   * Unique object for single session
   *
   * @return Command factory instance
   */
  @NotNull
  public static CommandFactory getInstance() {
    return Holder.INSTANCE;
  }

  /** Creates command by name and list of arguments
   * @param name
   *        The string representation of command name
   * @param args
   *        The list of command arguments
   * @return The specified command
   * @throws CommandCreationException if the constructor of command cannot be found
   */
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
