package com.spbau.bibaev.software.design.messenger.util;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * Contains utility methods for {@link String} class
 *
 * @author Vitaliy.Bibaev
 */
public class StringUtil {
  /**
   * Checks that {@code text} contains valid port tcp/ip port number
   *
   * @param text A candidate
   * @return true if value of {@code text} is valid port, otherwise false
   */
  public static boolean isContainsPortNumber(@NotNull String text) {
    final Scanner scanner = new Scanner(text.trim());
    if (!scanner.hasNextInt(10)) {
      return false;
    }

    final int val = scanner.nextInt(10);
    return !scanner.hasNextInt() && val > 0 && val < (2 << 16);
  }
}
