package com.spbau.bibaev.software.desing.shell.ex;

/**
 * Thrown if command name not specified
 * @author Vitaliy.Bibaev
 */
public class EmptyCommandException extends ParseException {
  /**
   * Constructs a new exception object
   *
   * @param message Description of the evaluation state
   */
  public EmptyCommandException(String message) {
    super(message);
  }
}
