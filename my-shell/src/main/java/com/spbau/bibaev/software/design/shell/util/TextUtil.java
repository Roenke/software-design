package com.spbau.bibaev.software.design.shell.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Utility methods for working with text and strings
 *
 * @author Vitaliy.Bibaev
 */
public class TextUtil {
  private final static char UNDERSCORE = '_';

  /**
   * Count number of words in {@code str}
   *
   * @param str the text
   * @return the number of words in {@code str}
   */
  public static int getWordCount(@NotNull String str) {
    return (int) Arrays.stream(str.trim().split("\\s+")).filter(x -> !x.isEmpty()).count();
  }

  /**
   * Check that {@code value} is valid identifier.
   * <p>Valid identifier is sequence of character starts with letter or
   * underscore character and next characters is numbers, letters or underscores</p>
   *
   * @param value the string for checking
   * @return true if {@code value} is valid identifier, otherwise false
   */
  public static boolean isIdentifier(@NotNull String value) {
    boolean isIdentifier = !value.isEmpty() &&
        (Character.isLetter(value.charAt(0)) || value.charAt(0) == UNDERSCORE);

    for (int i = 1; i < value.length(); i++) {
      char ch = value.charAt(i);
      isIdentifier = isIdentifier && Character.isLetterOrDigit(ch) || ch == UNDERSCORE;
    }

    return isIdentifier;
  }
}
