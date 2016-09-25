package com.spbau.bibaev.software.desing.shell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnvironmentTest {
  @Test
  public void unknownVariableReturnsEmptyString() {
    final String value = EnvironmentImpl.getInstance().getVariableValue("unknownVariable");
    assertTrue(value.isEmpty());
  }

  @Test
  public void overrideOldValue() {
    EnvironmentImpl.getInstance().putVariableValue("testVar", "10");
    assertEquals(EnvironmentImpl.getInstance().getVariableValue("testVar"), "10");
    EnvironmentImpl.getInstance().putVariableValue("testVar", "20");
    assertEquals(EnvironmentImpl.getInstance().getVariableValue("testVar"), "20");
  }
}
