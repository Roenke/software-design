package com.spbau.bibaev.software.design.shell.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextUtilTest {
  @Test
  public void emptyWords() {
    assertEquals(0, TextUtil.getWordCount(""));
  }

  @Test
  public void emptyWithoutTrimming() {
    assertEquals(0, TextUtil.getWordCount("         "));
  }

  @Test
  public void oneWordWithoutTrimming() {
    assertEquals(1, TextUtil.getWordCount("     hello    "));
  }

  @Test
  public void wordCount() {
    assertEquals(4, TextUtil.getWordCount("hello     my name\tis"));
  }

  @Test
  public void isIdentifier() {
    assertTrue(TextUtil.isIdentifier("variable"));
  }

  @Test
  public void isIdentifierUnderscore() {
    assertTrue(TextUtil.isIdentifier("variable_name"));
  }

  @Test
  public void isIdentifierNumbersContains() {
    assertTrue(TextUtil.isIdentifier("variable123name"));
  }

  @Test
  public void notIdentifierStartsWithNumber() {
    assertFalse(TextUtil.isIdentifier("1variable"));
  }

  @Test
  public void notIdentifierContainsMinus() {
    assertFalse(TextUtil.isIdentifier("variable-name"));
  }
}