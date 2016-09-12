package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.util.TextUtil;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WcCommand extends CommandBase {
  public WcCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @Override
  public void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {
    List<String> lines = reader.lines().collect(Collectors.toList());
    int lineCount = lines.size();
    int wordCount = 0;
    int characterCount = 0;
    for(String line : lines) {
      characterCount += line.length();
      wordCount += TextUtil.getWordCount(line);
    }

    writer.write(String.format("%d \t%d \t%d", lineCount, wordCount, characterCount));
    writer.flush();
  }
}
