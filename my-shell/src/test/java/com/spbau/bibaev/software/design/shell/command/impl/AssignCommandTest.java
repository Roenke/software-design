package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.EnvironmentImpl;
import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

public class AssignCommandTest extends ExecutableTestCase {
  @Test
  public void isSimpleAssignTest() {
    assertTrue(AssignCommand.isAssignExpression("x=10"));
    assertTrue(AssignCommand.isAssignExpression("x=abc"));
    assertTrue(AssignCommand.isAssignExpression("x=asdasdsad=asdasd"));
    assertFalse(AssignCommand.isAssignExpression("asd123="));
    assertFalse(AssignCommand.isAssignExpression("x="));
    assertFalse(AssignCommand.isAssignExpression("xa sdas="));
    assertFalse(AssignCommand.isAssignExpression("_="));
    assertFalse(AssignCommand.isAssignExpression("123sdf="));
  }

  @Test
  public void simpleVariableSetTest() throws IOException {
    new AssignCommand("x=abc", Collections.emptyList()).execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals("abc", EnvironmentImpl.getInstance().getVariableValue("x"));
  }

  @Test
  public void updateExistedValue() throws IOException {
    assertTrue(EnvironmentImpl.getInstance().getVariableValue("y").isEmpty());
    EnvironmentImpl.getInstance().putVariableValue("y", "hello");
    assertEquals("hello", EnvironmentImpl.getInstance().getVariableValue("y"));
    final ExecutionResult execute = new AssignCommand("y=hi", Collections.emptyList())
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);

    assertEquals(0, myOutputData.size());
    assertEquals(0, myErrorData.size());

    assertEquals("hi", EnvironmentImpl.getInstance().getVariableValue("y"));
  }
}