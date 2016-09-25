package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CompositeQuote implements Quote {
  private final List<Quote> myQuotes = new ArrayList<>();
  @Override
  public @NotNull String substitute(@NotNull Environment environment) {
    StringBuilder builder = new StringBuilder();
    for (Quote quote : myQuotes) {
      builder.append(quote.substitute(environment));
    }

    return builder.toString();
  }

  public void append(@NotNull Quote quote) {
    myQuotes.add(quote);
  }
}
