package com.spbau.bibaev.software.desing.shell.util;

import org.jetbrains.annotations.NotNull;

public class TextUtil {
  public static int getWordCount(@NotNull String str) {
    return str.split("\\s+").length;
  }
}
