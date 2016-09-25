package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.EmptyEnvironmentTestCase;
import com.spbau.bibaev.software.desing.shell.Environment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WeakQuoteTest extends EmptyEnvironmentTestCase {
  @Test
  public void simpleExpression() {
    Quote quote = new WeakQuote("value");
    Environment environment = getEmptyEnvironment();
    assertEquals("value", quote.substitute(environment));
  }

  @Test
  public void singleVariableExpression() {
    Quote quote = new WeakQuote("$VALUE");
    Environment environment = getEmptyEnvironment();
    environment.putVariableValue("VALUE", "YYY");
    assertNotEquals("$VALUE", quote.substitute(environment));
    assertEquals("YYY", quote.substitute(environment));
  }

  @Test
  public void fewVariableExpression() {
    Quote quote = new WeakQuote("$VALUE$X");
    Environment environment = getEmptyEnvironment();
    environment.putVariableValue("VALUE", "YYY");
    environment.putVariableValue("X", "ZZZ");
    assertEquals("YYYZZZ", quote.substitute(environment));
  }

  @Test
  public void variablesWithoutValueExpression() {
    Quote quote = new WeakQuote("$VALUE$X");
    Environment environment = getEmptyEnvironment();
    assertEquals("", quote.substitute(environment));
  }
}