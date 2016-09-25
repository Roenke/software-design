package com.spbau.bibaev.software.desing.shell.util;

import org.jetbrains.annotations.NotNull;

public class TextUtil {
  private final static char UNDERSCORE = '_';

  public static int getWordCount(@NotNull String str) {
    return str.split("\\s+").length;
  }

  public static boolean isIdentifier(@NotNull String value) {
    boolean isIdentifier = !value.isEmpty() &&
        (Character.isLetter(value.charAt(0)) || value.charAt(0) == UNDERSCORE);

    for (int i = 1; i < value.length(); i++){
      char ch = value.charAt(i);
      isIdentifier = isIdentifier && Character.isLetterOrDigit(ch) || ch == UNDERSCORE;
    }

    return isIdentifier;
  }
}
