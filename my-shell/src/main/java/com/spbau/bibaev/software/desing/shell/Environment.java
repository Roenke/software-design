package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Environment {
  private final Map<String, String> myVariables = new HashMap<>();

  private Environment() {
  }

  private static class Holder {
    static final Environment INSTANCE = new Environment();
  }

  public static Environment getInstance() {
    return Holder.INSTANCE;
  }

  @NotNull
  public String getVariableValue(@NotNull String variable) {
    return myVariables.getOrDefault(variable, "");
  }

  public void putVariableValue(@NotNull String variable, @NotNull String value) {
    myVariables.put(variable, value);
  }
}
