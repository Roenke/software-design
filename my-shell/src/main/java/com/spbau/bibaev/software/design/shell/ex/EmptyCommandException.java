package com.spbau.bibaev.software.design.shell.ex;

/**
 * Thrown if command name not specified
 *
 * @author Vitaliy.Bibaev
 */
public class EmptyCommandException extends ParseException {
  /**
   * Constructs a new exception object
   *
   * @param message description of the evaluation state
   */
  public EmptyCommandException(String message) {
    super(message);
  }
}
