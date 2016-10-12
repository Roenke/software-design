package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

/**
 * The environment of execution. Contains variables and current directory
 *
 * @author Vitaliy.Bibaev
 */
public interface Environment {
  /**
   * Return the value of {@code variable} by name
   *
   * @param variable The name of a variable
   * @return The value of variable if the variable existed in environment,
   * otherwise empty string
   */
  String getVariableValue(@NotNull String variable);

  /**
   * Add new variable to execution environment
   *
   * @param variable The name of variable
   * @param value    The value of {@code variable}
   */
  void putVariableValue(@NotNull String variable, @NotNull String value);

  /**
   * Returns current working directory
   *
   * @return The absolute path to working directory
   */
  Path getCurrentDirectory();
}
