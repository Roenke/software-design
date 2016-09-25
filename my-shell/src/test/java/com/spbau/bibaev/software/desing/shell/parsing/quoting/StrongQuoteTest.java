package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import com.spbau.bibaev.software.desing.shell.EnvironmentImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrongQuoteTest {

  @Test
  public void simpleExpression() {
    Quote quote = new StrongQuote("value");
    Environment environment = EnvironmentImpl.getInstance();
    assertEquals("value", quote.substitute(environment));
  }

  @Test
  public void singleVariableExpression() {
    Quote quote = new StrongQuote("$VALUE");
    Environment environment = EnvironmentImpl.getInstance();
    environment.putVariableValue("VALUE", "YYY");
    assertNotEquals("YYY", quote.substitute(environment));
    assertEquals("$VALUE", quote.substitute(environment));
  }

  @Test
  public void fewVariableExpression() {
    Quote quote = new StrongQuote("$VALUE$X");
    Environment environment = EnvironmentImpl.getInstance();
    environment.putVariableValue("VALUE", "YYY");
    environment.putVariableValue("Y", "ZZZ");
    assertEquals("$VALUE$X", quote.substitute(environment));
  }
}