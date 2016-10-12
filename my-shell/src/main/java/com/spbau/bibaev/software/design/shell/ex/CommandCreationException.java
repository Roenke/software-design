package com.spbau.bibaev.software.design.shell.ex;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown to indicate that command creation failed. The constructor is either inaccessible of not found
 *
 * @author Vitaliy.Bibaev
 */
public class CommandCreationException extends Exception {

  /**
   * Creates new exception instance
   *
   * @param name the name of command
   * @param cause the reason of the exception
   */
  public CommandCreationException(@NotNull String name, @NotNull Throwable cause) {
    super("Cannot create command " + name, cause);
  }
}
