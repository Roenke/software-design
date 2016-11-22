package com.spbau.bibaev.software.design.shell.command;

/**
 * Value is used for represent result of command evaluation
 *
 * @author Vitaliy.Bibaev
 * @see Executable
 */
public enum ExecutionResult {

  /**
   * Evaluation is able to continue
   */
  CONTINUE,

  /**
   * Evaluation should be interrupted
   */
  SHUTDOWN
}
