package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CatCommand extends CommandBase {
  private static final Logger LOG = LogManager.getLogger(CatCommand.class);

  public CatCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @Override
  public void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {
    if (ourArgs.size() > 1) {
      CommandArg filename = ourArgs.get(1);
      BufferedReader fileReader = new BufferedReader(new FileReader(filename.getValue()));
      performImpl(fileReader, writer);
    }

    performImpl(reader, writer);
  }

  private void performImpl(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {
    for (String line : (Iterable<String>) reader.lines()::iterator) {
      writer.write(line);
      writer.newLine();
      writer.flush();
    }
  }
}
