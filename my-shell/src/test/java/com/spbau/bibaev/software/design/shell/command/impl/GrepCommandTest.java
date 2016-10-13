package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.command.ExecutableTestCase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GrepCommandTest extends ExecutableTestCase {
  @Rule
  public TemporaryFolder rule = new TemporaryFolder();

  @Test
  public void emptyTest() throws IOException {
    ExecutionResult result = new GrepCommand("grep", Collections.singletonList("pattern"))
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);

    assertEquals(ExecutionResult.CONTINUE, result);
    assertTrue(getOutputString().trim().isEmpty());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void simpleMatchOneLine() throws IOException {
    String line = "pattern";
    ExecutionResult result = new GrepCommand("grep", Collections.singletonList(line))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(line, getOutputString().trim());
    assertTrue(getErrorString().isEmpty());
  }

  @Test
  public void notMatchCaseSensitively() throws IOException {
    String line = "pattern";
    ExecutionResult result = new GrepCommand("grep", Collections.singletonList("Pattern"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertTrue(getOutputString().trim().isEmpty());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void matchIgnoreCase() throws IOException {
    String line = "PatterN";
    ExecutionResult result = new GrepCommand("grep", Arrays.asList("pattern", "-i"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(line, getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void entireWordMatching() throws IOException {
    String line = "pattern";
    ExecutionResult result = new GrepCommand("grep", Collections.singletonList("pat"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(line, getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void entireWordKeyUsing() throws IOException {
    String line = "pattern";
    ExecutionResult result = new GrepCommand("grep", Arrays.asList("pat", "-w"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertTrue(getOutputString().trim().isEmpty());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void regexUsage() throws IOException {
    String line = "aa bb cc ee dd";
    ExecutionResult result = new GrepCommand("grep", Collections.singletonList("a*\\s*b+\\s[bed\\s]*"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(line, getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void nextLinesAfterMatchingWhenNoMatching() throws IOException {
    String line = "first line" + System.lineSeparator() + " second line";
    ExecutionResult result = new GrepCommand("grep", Arrays.asList("zzz", "-A", "1"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertTrue(getOutputString().trim().isEmpty());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void nextLinesAfterMatchingWhenMatchingExists() throws IOException {
    String line = "first line" + System.lineSeparator() + "second line" + System.lineSeparator() + "third line";
    ExecutionResult result = new GrepCommand("grep", Arrays.asList("first", "-A", "1"))
        .execute(new ByteArrayInputStream(line.getBytes()), myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(("first line" + System.lineSeparator() + "second line"), getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());
  }

  @Test
  public void usingFile() throws IOException {
    final Path tempFile = Files.createTempFile("test", "regex");
    try(OutputStream os = Files.newOutputStream(tempFile)) {
      PrintStream printer = new PrintStream(os);
      printer.println("mysql");
      printer.println("python");
      printer.println("ruby");
      printer.println("java");
      printer.println("C++");
      printer.println("Groovy");
    }

    ExecutionResult result = new GrepCommand("grep", Arrays.asList("(python|groovy)",
        tempFile.toAbsolutePath().toString(), "-i", "-A", "1"))
        .execute(EMPTY_INPUT_STREAM, myOutputStream, myErrorStream);
    assertEquals(ExecutionResult.CONTINUE, result);
    assertEquals(("python" + System.lineSeparator() + "ruby" + System.lineSeparator() + "Groovy"),
        getOutputString().trim());
    assertTrue(getErrorString().trim().isEmpty());

    Files.delete(tempFile);
  }
}
