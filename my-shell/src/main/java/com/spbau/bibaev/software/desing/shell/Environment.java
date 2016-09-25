package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

public interface Environment {
  String getVariableValue(@NotNull String variable);
  void putVariableValue(@NotNull String variable, @NotNull String value);
}
