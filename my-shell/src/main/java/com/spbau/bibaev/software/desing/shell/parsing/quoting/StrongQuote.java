package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

public class StrongQuote extends QuoteBase {

  public StrongQuote(@NotNull String string, int begin, int end) {
    super(string, begin, end);
  }

  @Override
  protected @NotNull String substitute(@NotNull String body, @NotNull Environment environment) {
    return body;
  }
}
