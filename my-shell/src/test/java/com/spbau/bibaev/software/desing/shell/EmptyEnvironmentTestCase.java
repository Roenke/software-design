package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EmptyEnvironmentTestCase {
  protected Environment getEmptyEnvironment() {
    return new Environment() {
      private final Map<String, String> map = new HashMap<>();
      @Override
      public String getVariableValue(@NotNull String variable) {
        return map.getOrDefault(variable, "");
      }

      @Override
      public void putVariableValue(@NotNull String variable, @NotNull String value) {
        map.put(variable, value);
      }
    };
  }
}
