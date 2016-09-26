package com.spbau.bibaev.software.desing.shell;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation for {@link Environment}. A Singleton object
 *
 * @author Vitaliy.Bibaev
 */
public class EnvironmentImpl implements Environment {
  private final Map<String, String> myVariables = new HashMap<>();
  private final Path myCurrentDirectory;

  private EnvironmentImpl() {
    myCurrentDirectory = Paths.get(System.getProperty("user.dir"));
  }

  private static class Holder {
    static final EnvironmentImpl INSTANCE = new EnvironmentImpl();
  }

  /**
   * Returns the {@link Environment} instance
   *
   * @return The {@link Environment}
   */
  public static Environment getInstance() {
    return Holder.INSTANCE;
  }

  @NotNull
  public String getVariableValue(@NotNull String variable) {
    if (myVariables.containsKey(variable)) {
      return myVariables.get(variable);
    }

    return System.getenv().getOrDefault(variable, "");
  }

  public void putVariableValue(@NotNull String variable, @NotNull String value) {
    myVariables.put(variable, value);
  }

  @NotNull
  @Override
  public Path getCurrentDirectory() {
    return myCurrentDirectory;
  }
}
