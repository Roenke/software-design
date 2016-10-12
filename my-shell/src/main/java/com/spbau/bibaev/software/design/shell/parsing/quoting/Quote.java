package com.spbau.bibaev.software.design.shell.parsing.quoting;

import com.spbau.bibaev.software.design.shell.Environment;
import org.jetbrains.annotations.NotNull;

/**
 * Basic interface for something that can be replace some content for another in
 * some environment
 *
 * @author Vitaliy.Bibaev
 */
public interface Quote {
  /**
   * Substitute expression according with the {@code environment}
   *
   * @param environment The current execution environment
   * @return The result of substitution
   */
  @NotNull
  String substitute(@NotNull Environment environment);
}
