package com.spbau.bibaev.software.desing.shell.ex;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown if parsing failed
 *
 * @author Vitaliy.Bibaev
 */
public class ParseException extends Exception {
  ParseException(@NotNull String message) {
    super(message);
  }
}
