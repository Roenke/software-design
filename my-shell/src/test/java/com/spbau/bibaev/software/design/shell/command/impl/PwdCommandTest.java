package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.EnvironmentImpl;
import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

public class PwdCommandTest extends ExecutableTestCase{
  @Test
  public void pwdTest() throws IOException {
    String currentDirectory = EnvironmentImpl.getInstance().getCurrentDirectory().toAbsolutePath().toString();
    ExecutionResult result = new PwdCommand("pwd", Collections.emptyList())
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(currentDirectory, getOutputString().trim());
  }
}
