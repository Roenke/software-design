package com.spbau.bibaev.software.desing.shell.parsing;

import com.spbau.bibaev.software.desing.shell.ex.ParseException;
import org.jetbrains.annotations.NotNull;

/**
 * Basic interface for all parsers
 * @param <T> The result of parse operation
 */
public interface Parser<T> {

  /**
   * Parses {@code input} and returns something
   * @param input The input string
   * @return The result of parsing operation
   * @throws ParseException Thrown if something wrong in input string
   */
  T parse(@NotNull String input) throws ParseException;
}
