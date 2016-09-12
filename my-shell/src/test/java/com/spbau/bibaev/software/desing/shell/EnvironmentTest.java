package com.spbau.bibaev.software.desing.shell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnvironmentTest {
  @Test
  public void unknownVariableReturnsEmptyString() {
    final String value = Environment.getInstance().getVariableValue("unknownVariable");
    assertTrue(value.isEmpty());
  }

  @Test
  public void overrideOldValue() {
    Environment.getInstance().putVariableValue("testVar", "10");
    assertEquals(Environment.getInstance().getVariableValue("testVar"), "10");
    Environment.getInstance().putVariableValue("testVar", "20");
    assertEquals(Environment.getInstance().getVariableValue("testVar"), "20");
  }
}
