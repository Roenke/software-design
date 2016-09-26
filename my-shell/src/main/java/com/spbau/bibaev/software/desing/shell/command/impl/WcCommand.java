package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.util.TextUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Count lines, word and characters in input stream
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class WcCommand extends CommandBase {
  /**
   * Constructs a new command
   *
   * @param name The name of command - "wc"
   * @param args The list of arguments
   */
  public WcCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    List<String> lines = reader.lines().collect(Collectors.toList());
    int lineCount = lines.size();
    int wordCount = 0;
    int characterCount = 0;
    for (String line : lines) {
      characterCount += line.length();
      wordCount += TextUtil.getWordCount(line);
    }

    out.write(String.format("%d \t%d \t%d", lineCount, wordCount, characterCount).getBytes());
    out.write(System.lineSeparator().getBytes());

    return ExecutionResult.CONTINUE;
  }
}
