package com.spbau.bibaev.software.desing.shell.util;

import org.jetbrains.annotations.NotNull;

public class TextUtil {
  public static int getWordCount(@NotNull String str) {
    return str.split("\\s+").length;
  }
  public static boolean isIdentifier(@NotNull String value) {
    if(value.isEmpty() || Character.isLetter(value.charAt(0))) {
      return false;
    }

    for(int i = 1; i < value.length(); i++) {
      if(!Character.isLetterOrDigit(value.charAt(i))) {
        return false;
      }
    }

    return true;
  }
}
