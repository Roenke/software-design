package com.spbau.bibaev.software.design.shell.parsing.quoting;

import com.spbau.bibaev.software.design.shell.EmptyEnvironmentTestCase;
import com.spbau.bibaev.software.design.shell.Environment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

public class WeakQuoteTest implements EmptyEnvironmentTestCase {
  @Test
  public void simpleExpression() {
    Quote quote = WeakQuote.create("value");
    Environment environment = getEmptyEnvironment();
    assertEquals("value", quote.substitute(environment));
  }

  @Test
  public void singleVariableExpression() {
    Quote quote = WeakQuote.create("$VALUE");
    Environment environment = getEmptyEnvironment();
    environment.putVariableValue("VALUE", "YYY");
    assertNotEquals("$VALUE", quote.substitute(environment));
    assertEquals("YYY", quote.substitute(environment));
  }

  @Test
  public void fewVariableExpression() {
    Quote quote = WeakQuote.create("$VALUE$X");
    Environment environment = getEmptyEnvironment();
    environment.putVariableValue("VALUE", "YYY");
    environment.putVariableValue("X", "ZZZ");
    assertEquals("YYYZZZ", quote.substitute(environment));
  }

  @Test
  public void variablesWithoutValueExpression() {
    Quote quote = WeakQuote.create("$VALUE$X");
    Environment environment = getEmptyEnvironment();
    assertEquals("", quote.substitute(environment));
  }

  @Test
  public void cachedWeakQuote() {
    Quote quote = WeakQuote.create("abc");
    assertSame(quote, WeakQuote.create("abc"));
  }
}
