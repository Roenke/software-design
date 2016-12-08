package com.spbau.bibaev.software.design.shell.parsing.quoting;

import com.spbau.bibaev.software.design.shell.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * The quote which replace all entries of variables for their values in
 * current environment
 *
 * @author Vitaliy.Bibaev
 * @see Quote
 * @see Environment
 */
public class WeakQuote implements Quote {
  private static final char VARIABLE_PREFIX = '$';
  private final String myString;

  private WeakQuote(@NotNull String string) {
    myString = string;
  }

  /**
   * Constructs a new weak quote or get existed (if it was created earlier and not collected yet)
   *
   * @param string The string for substitution
   */
  public static Quote create(@NotNull String string) {
    return MyWeakQuotePool.Holder.POOL.get(string);
  }

  /**
   * Replace all entries of {@code environment} variables for their values
   *
   * @param environment The current execution environment
   * @return The string after all substitutions
   */
  @NotNull
  @Override
  public String substitute(@NotNull Environment environment) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < myString.length()) {
      if (myString.charAt(i) == VARIABLE_PREFIX && i + 1 < myString.length() &&
          Character.isLetter(myString.charAt(i + 1))) {
        int start = i + 1;
        i += 2;
        while (i < myString.length() && Character.isLetterOrDigit(myString.charAt(i))) {
          i++;
        }

        String variableName = myString.substring(start, i);
        sb.append(environment.getVariableValue(variableName));
      } else {
        sb.append(myString.charAt(i++));
      }
    }

    return sb.toString();
  }

  private static class MyWeakQuotePool {
    private final Map<String, Quote> myQuoteCache = new WeakHashMap<>();

    private static class Holder {
      static final MyWeakQuotePool POOL = new MyWeakQuotePool();
    }

    Quote get(@NotNull String string) {
      return myQuoteCache.computeIfAbsent(string, WeakQuote::new);
    }
  }
}
