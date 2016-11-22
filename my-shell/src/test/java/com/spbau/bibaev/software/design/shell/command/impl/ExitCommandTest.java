package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ExitCommandTest extends ExecutableTestCase {
  @Test
  public void exitTest() throws IOException {
    ExecutionResult result = new ExitCommand("exit", Collections.emptyList())
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.SHUTDOWN, result);
  }
}
