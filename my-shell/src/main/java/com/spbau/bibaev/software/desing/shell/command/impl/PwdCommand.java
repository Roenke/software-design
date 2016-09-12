package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class PwdCommand extends CommandBase {
  public PwdCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @Override
  public void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {
    writer.write(System.getProperty("user.dir"));
    writer.newLine();
    writer.flush();
  }
}
