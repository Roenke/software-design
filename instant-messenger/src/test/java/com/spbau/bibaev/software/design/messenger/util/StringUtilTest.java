package com.spbau.bibaev.software.design.messenger.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vitaliy.Bibaev
 */
public class StringUtilTest {
  @Test
  public void negativeAndZeroNotPasses() {
    assertFalse(StringUtil.isContainsPortNumber("-1"));
    assertFalse(StringUtil.isContainsPortNumber("-10"));
    assertFalse(StringUtil.isContainsPortNumber("-100"));
    assertFalse(StringUtil.isContainsPortNumber("0"));
    assertFalse(StringUtil.isContainsPortNumber("-0"));
  }

  @Test
  public void floatNotPasses() {
    assertFalse(StringUtil.isContainsPortNumber("-1."));
    assertFalse(StringUtil.isContainsPortNumber("10.23"));
    assertFalse(StringUtil.isContainsPortNumber("1."));
    assertFalse(StringUtil.isContainsPortNumber("2354.343"));
    assertFalse(StringUtil.isContainsPortNumber("1f"));
  }

  @Test
  public void withLettersNotPasses() {
    assertFalse(StringUtil.isContainsPortNumber("23ysd"));
    assertFalse(StringUtil.isContainsPortNumber("t6435"));
    assertFalse(StringUtil.isContainsPortNumber("3455 435"));
    assertFalse(StringUtil.isContainsPortNumber("3 5"));
  }

  @Test
  public void correctPasses() {
    assertTrue(StringUtil.isContainsPortNumber("1"));
    assertTrue(StringUtil.isContainsPortNumber("20"));
    assertTrue(StringUtil.isContainsPortNumber("65535"));
    assertTrue(StringUtil.isContainsPortNumber("80"));
    assertTrue(StringUtil.isContainsPortNumber("  80"));
    assertTrue(StringUtil.isContainsPortNumber("80  "));
    assertTrue(StringUtil.isContainsPortNumber("  80  "));
  }
}