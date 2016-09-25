package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteBaseTest {
  @Test
  public void simpleStringQuote() {
    Environment environment = Environment.getInstance();
    Quote quote = QuoteBase.makeQuote("cat");
    assertEquals("cat", quote.substitute(environment));
  }

  @Test
  public void quotedVariableUsage() {
    Environment environment = Environment.getInstance();
    environment.putVariableValue("X", "cat");
    Quote quote = QuoteBase.makeQuote("\"$X\"");
    assertEquals("cat", quote.substitute(environment));
  }

  @Test
  public void unquotedVariableUsage() {
    Environment environment = Environment.getInstance();
    environment.putVariableValue("X", "cat");
    Quote quote = QuoteBase.makeQuote("$X");
    assertEquals("cat", quote.substitute(environment));
  }

  @Test
  public void weakQuoteUsage() {
    Environment environment = Environment.getInstance();
    environment.putVariableValue("$X", "cat");
    Quote quote = QuoteBase.makeQuote("'$X'");
    String result = quote.substitute(environment);
    assertNotEquals("cat", result);
    assertEquals("$X", result);
  }

  @Test
  public void fewQuotes() {
    Environment environment = Environment.getInstance();
    environment.putVariableValue("$X", "cat");
    environment.putVariableValue("$Y", "wc");
    assertEquals("catAnd-wc", QuoteBase.makeQuote("\"$X\"And-$Y").substitute(environment));
  }
}
