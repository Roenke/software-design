package com.spbau.bibaev.software.design.shell.pipe;

import com.spbau.bibaev.software.design.shell.command.Executable;
import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import com.spbau.bibaev.software.design.shell.command.impl.CatCommand;
import com.spbau.bibaev.software.design.shell.command.impl.EchoCommand;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

public class PipeHandlerTest extends ExecutableTestCase {

  @Test
  public void executeBothCommand() throws IOException {
    boolean[] executed = new boolean[2];
    Executable first = (in, out, err) -> {
      executed[0] = true;
      return ExecutionResult.CONTINUE;
    };

    Executable second = (in, out, err) -> {
      executed[1] = true;
      return ExecutionResult.CONTINUE;
    };

    ExecutionResult result = new PipeHandler(first, second).execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertTrue(executed[0]);
    assertTrue(executed[1]);
  }

  @Test
  public void interruptPipeline() throws IOException {
    boolean[] executed = new boolean[2];
    Executable first = (in, out, err) -> {
      executed[0] = true;
      return ExecutionResult.SHUTDOWN;
    };

    Executable second = (in, out, err) -> {
      executed[1] = true;
      return ExecutionResult.CONTINUE;
    };

    ExecutionResult result = new PipeHandler(first, second).execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.SHUTDOWN, result);
    assertTrue(executed[0]);
    assertFalse(executed[1]);
  }

  @Test
  public void pipeContentCorrect() throws IOException {
    String content = "hello";
    Executable echo = new EchoCommand("echo", Collections.singletonList(content));
    Executable cat = new CatCommand("cat", Collections.emptyList());
    ExecutionResult result = new PipeHandler(echo, cat).execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(content, getOutputString().trim());
  }
}
