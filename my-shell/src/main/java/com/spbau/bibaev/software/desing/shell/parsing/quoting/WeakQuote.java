package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

public class WeakQuote extends QuoteBase {
  private static char VARIABLE_PREFIX = '$';
  public WeakQuote(@NotNull String string, int begin, int end) {
    super(string, begin, end);
  }

  @Override
  @NotNull
  protected String substitute(@NotNull String body, @NotNull Environment environment) {
    return "";
  }
}
