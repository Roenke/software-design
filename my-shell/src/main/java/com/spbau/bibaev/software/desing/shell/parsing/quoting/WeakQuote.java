package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.apache.logging.log4j.core.config.Scheduled;
import org.jetbrains.annotations.NotNull;

public class WeakQuote implements Quote {
  private static char VARIABLE_PREFIX = '$';
  private final String myString;

  public WeakQuote(@NotNull String string) {
    myString = string;
  }


  @Override
  public @NotNull String substitute(@NotNull Environment environment) {
    return myString;
  }
}
