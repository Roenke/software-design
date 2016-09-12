package com.spbau.bibaev.software.desing.shell.pipe;

import com.spbau.bibaev.software.desing.shell.Executable;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class PipeHandler implements Executable {
  private final Executable myLeftExecutable;
  private final Executable myRightExecutable;

  public PipeHandler(@NotNull Executable left, @NotNull Executable right) {
    myLeftExecutable = left;
    myRightExecutable = right;
  }

  @Override
  public void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedWriter writer1 = new BufferedWriter(new StringBuilderWriter(sb));
    myLeftExecutable.perform(reader, writer1);
    BufferedReader reader1 = new BufferedReader(new StringReader(sb.toString()));
    myRightExecutable.perform(reader1, writer);
  }
}
