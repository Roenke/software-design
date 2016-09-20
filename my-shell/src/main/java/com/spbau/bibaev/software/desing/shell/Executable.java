package com.spbau.bibaev.software.desing.shell;


import org.jetbrains.annotations.NotNull;

import java.io.*;

public interface Executable {
  @NotNull
  ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException;
}
