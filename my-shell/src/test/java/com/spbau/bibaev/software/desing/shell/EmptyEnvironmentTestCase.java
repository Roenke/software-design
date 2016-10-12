package com.spbau.bibaev.software.desing.shell;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("NullableProblems")
public interface EmptyEnvironmentTestCase {
  default Environment getEmptyEnvironment() {
    return new Environment() {
      private final Map<String, String> map = new HashMap<>();

      @Override
      public String getVariableValue(String variable) {
        return map.getOrDefault(variable, "");
      }

      @Override
      public void putVariableValue(String variable, String value) {
        map.put(variable, value);
      }

      @Override
      public Path getCurrentDirectory() {
        return Paths.get(System.getProperty("user.dir"));
      }
    };
  }
}
