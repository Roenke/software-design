package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentImpl implements Environment {
  private final Map<String, String> myVariables = new HashMap<>();

  private EnvironmentImpl() {
  }

  private static class Holder {
    static final EnvironmentImpl INSTANCE = new EnvironmentImpl();
  }

  public static EnvironmentImpl getInstance() {
    return Holder.INSTANCE;
  }

  @NotNull
  public String getVariableValue(@NotNull String variable) {
    if(myVariables.containsKey(variable)) {
      return myVariables.get(variable);
    }

    return System.getenv().getOrDefault(variable, "");
  }

  public void putVariableValue(@NotNull String variable, @NotNull String value) {
    myVariables.put(variable, value);
  }
}
