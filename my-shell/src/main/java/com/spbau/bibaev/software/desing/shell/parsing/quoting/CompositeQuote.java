package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Quote which consist of few another quotes
 * <p>Example: {@code pre"$FIX"'$suffix'} will be represents as 3 quotes</p>
 *
 * @author Vitaliy.Bibaev
 * @see Quote
 */
public class CompositeQuote implements Quote {
  private final List<Quote> myQuotes = new ArrayList<>();

  /**
   * Concatenate all inner quotes results
   *
   * @param environment The current execution environment
   * @return The result of the substitution
   */
  @NotNull
  @Override
  public String substitute(@NotNull Environment environment) {
    StringBuilder builder = new StringBuilder();
    for (Quote quote : myQuotes) {
      builder.append(quote.substitute(environment));
    }

    return builder.toString();
  }

  /**
   * Add to {@code quote} to end
   *
   * @param quote The quote for substitution
   */
  public void append(@NotNull Quote quote) {
    myQuotes.add(quote);
  }
}
