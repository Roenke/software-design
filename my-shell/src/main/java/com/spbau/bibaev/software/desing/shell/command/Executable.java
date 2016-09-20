package com.spbau.bibaev.software.desing.shell.command;


import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public interface Executable {
  @NotNull
  ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException;
}
