package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

public class StrongQuote implements Quote {

  private final String myString;

  public StrongQuote(@NotNull String string) {
    myString = string;
  }

  @NotNull
  @Override
  public String substitute(@NotNull Environment environment) {
    return myString;
  }
}
