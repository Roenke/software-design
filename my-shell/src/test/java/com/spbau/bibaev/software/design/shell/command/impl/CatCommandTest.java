package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CatCommandTest extends ExecutableTestCase {
  @Rule
  public TemporaryFolder myTemporaryFolder = new TemporaryFolder();

  @Test
  public void inputStreamTest() throws IOException {
    InputStream is = new ByteArrayInputStream("hello".getBytes());
    final ExecutionResult result = new CatCommand("cat", Collections.emptyList())
        .execute(is, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);

    assertEquals("hello", getOutputString());
  }

  @Test
  public void fileTest() throws IOException {
    String filename = "test";
    String contentOfFile = "hello!!!";
    final File test = myTemporaryFolder.newFile(filename);
    Files.write(test.toPath().toAbsolutePath(), contentOfFile.getBytes());
    final ExecutionResult result = new CatCommand("cat",
        Collections.singletonList(test.toPath().toAbsolutePath().toString()))
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);

    assertEquals(contentOfFile, getOutputString());
    assertTrue(getErrorString().isEmpty());
  }
}
