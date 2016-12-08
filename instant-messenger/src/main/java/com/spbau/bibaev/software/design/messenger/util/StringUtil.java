package com.spbau.bibaev.software.design.messenger.util;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author Vitaliy.Bibaev
 */
public class StringUtil {
  public static boolean isContainsPortNumber(@NotNull String text) {
    Scanner scanner = new Scanner(text.trim());
    if (!scanner.hasNextInt(10)) {
      return false;
    }

    int val = scanner.nextInt(10);
    return !scanner.hasNextInt() && val > 0 && val < (2 << 16);
  }
}
