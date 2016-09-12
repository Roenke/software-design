package com.spbau.bibaev.software.desing.shell.command.impl.todo;

import com.spbau.bibaev.software.desing.shell.command.CommandArg;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class AssignCommand extends CommandBase {
  public AssignCommand(@NotNull List<CommandArg> args) {
    super(args);
  }

  @Override
  public void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {

  }
}
