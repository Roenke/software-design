package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

/**
 * Free-substitution quote
 *
 * @author Vitaliy.Bibaev
 * @see Quote
 */
public class StrongQuote implements Quote {

  private final String myString;

  /**
   * Constructs new Strong quote
   *
   * @param string The string for substitution
   */
  public StrongQuote(@NotNull String string) {
    myString = string;
  }

  @NotNull
  @Override
  public String substitute(@NotNull Environment environment) {
    return myString;
  }
}
