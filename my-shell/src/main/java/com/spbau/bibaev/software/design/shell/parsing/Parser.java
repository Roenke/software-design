package com.spbau.bibaev.software.design.shell.parsing;

import com.spbau.bibaev.software.design.shell.ex.ParseException;
import org.jetbrains.annotations.NotNull;

/**
 * Basic interface for all parsers
 *
 * @param <T> the result of parse operation
 */
public interface Parser<T> {

  /**
   * Parses {@code input} and returns something
   *
   * @param input the input string
   * @return the result of parsing operation
   * @throws ParseException thrown if something wrong in input string
   */
  T parse(@NotNull String input) throws ParseException;
}
