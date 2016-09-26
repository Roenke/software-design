package com.spbau.bibaev.software.desing.shell.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IoStreamUtils {
  private static final int BUFFER_SIZE = 4096;

  public static void copy(@NotNull InputStream in, @NotNull OutputStream out)
      throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int readCount = in.read(buffer);
    while(readCount > 0) {
      out.write(buffer, 0, readCount);
      readCount = in.read(buffer);
    }
  }
}
