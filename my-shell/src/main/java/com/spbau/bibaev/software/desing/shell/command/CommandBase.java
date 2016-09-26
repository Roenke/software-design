package com.spbau.bibaev.software.desing.shell.command;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Base class for all commands.
 * If you want to declare custom command, you need inherit this class, and match constructor CommandBase(2)
 * Also you need add your command to map inside of {@link CommandFactory}
 *
 * @author Vitaliy.Bibaev
 * @see CommandFactory
 * @see Executable
 */
public abstract class CommandBase implements Executable {
  private final String myName;
  private final List<String> myArgs;

  /**
   * Only for subclasses
   *
   * @param name name of command
   * @param args command arguments
   */
  protected CommandBase(@NotNull String name, @NotNull List<String> args) {
    myName = name;
    myArgs = args;
  }

  /**
   * Returns a name of command (name may be used)
   *
   * @return String represents name of the command
   */
  @NotNull
  protected String getName() {
    return myName;
  }

  /**
   * Returns list of command arguments (with all substitutions)
   *
   * @return list if
   */
  @NotNull
  protected List<String> getArgs() {
    return myArgs;
  }
}
