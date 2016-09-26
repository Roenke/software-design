package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface Environment {
  @NotNull
  String getVariableValue(@NotNull String variable);
  void putVariableValue(@NotNull String variable, @NotNull String value);
  @NotNull
  Path getCurrentDirectory();
}
