package com.spbau.bibaev.software.desing.shell;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("NullableProblems")
public class EmptyEnvironmentTestCase {
  protected Environment getEmptyEnvironment() {
    return new Environment() {
      private final Map<String, String> map = new HashMap<>();
      @Override
      public String getVariableValue( String variable) {
        return map.getOrDefault(variable, "");
      }

      @Override
      public void putVariableValue(String variable, String value) {
        map.put(variable, value);
      }
    };
  }
}
