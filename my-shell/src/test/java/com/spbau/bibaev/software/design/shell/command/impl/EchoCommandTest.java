package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoCommandTest extends ExecutableTestCase {
  @Test
  public void oneArgumentTest() throws IOException {
    String arg = "hello";
    ExecutionResult result = new EchoCommand("echo", Collections.singletonList(arg))
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);

    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(arg, getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void fewArgumentsTest() throws IOException {
    String arg1 = "arg1";
    String arg2 = "arg2";
    List<String> args = new ArrayList<>();
    args.add(arg1);
    args.add(arg2);
    ExecutionResult result = new EchoCommand("echo", args)
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);

    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(arg1 + ' ' + arg2, getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }
}
