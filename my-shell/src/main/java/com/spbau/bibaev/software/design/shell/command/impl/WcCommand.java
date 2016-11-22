package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.command.CommandBase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import com.spbau.bibaev.software.design.shell.util.TextUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
   * @param name the name of command - "wc"
   * @param args the list of arguments
   */
  public WcCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    AtomicInteger charCount = new AtomicInteger(0);
    BufferedReader reader = new BufferedReader(new InputStreamReader(new InputStream() {
      @Override
      public int read() throws IOException {
        int result = in.read();
        if (result != -1) {
          charCount.incrementAndGet();
        }

        return result;
      }
    }));
    List<String> lines = reader.lines().collect(Collectors.toList());
    int lineCount = lines.size();
    int wordCount = 0;
    for (String line : lines) {
      wordCount += TextUtil.getWordCount(line);
    }

    out.write(String.format("%d \t%d \t%d", lineCount, wordCount, charCount.get()).getBytes());
    out.write(System.lineSeparator().getBytes());

    return ExecutionResult.CONTINUE;
  }
}
