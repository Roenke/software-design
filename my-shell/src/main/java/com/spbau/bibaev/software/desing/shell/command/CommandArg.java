package com.spbau.bibaev.software.desing.shell.command;

import com.spbau.bibaev.software.desing.shell.EnvironmentImpl;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.Quote;
import org.jetbrains.annotations.NotNull;

/**
 * The argument of command.
 *
 * @author Vitaliy.Bibaev
 * @see Quote
 */
public class CommandArg {
  private final Quote myQuote;

  /**
   * Constructs new argument
   *
   * @param quote the internal quote from user input
   */
  public CommandArg(@NotNull Quote quote) {
    myQuote = quote;
  }

  /**
   * Returns raw string representation of the argument with all substitutions
   *
   * @return the string representation of the argument
   */
  @NotNull
  public String getValue() {
    return myQuote.substitute(EnvironmentImpl.getInstance());
  }
}
