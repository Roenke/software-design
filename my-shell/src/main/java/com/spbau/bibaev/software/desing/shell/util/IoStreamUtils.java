package com.spbau.bibaev.software.desing.shell.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility methods for Input/Output stream in java
 */
public class IoStreamUtils {
  private static final int BUFFER_SIZE = 4096;

  /**
   * Copy all content of {@code in} stream into output stream. Does not close any stream
   *
   * @param in the source stream
   * @param out the destination stream
   * @throws IOException thrown if any stream reading/writing throws an exception
   */
  public static void copy(@NotNull InputStream in, @NotNull OutputStream out)
      throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int readCount = in.read(buffer);
    while (readCount > 0) {
      out.write(buffer, 0, readCount);
      readCount = in.read(buffer);
    }
  }
}
