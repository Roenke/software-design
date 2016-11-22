package com.spbau.bibaev.software.design.shell.ex;

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
