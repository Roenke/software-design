package com.spbau.bibaev.software.desing.shell.parsing;

import com.spbau.bibaev.software.desing.shell.EmptyEnvironmentTestCase;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.Quote;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuoteParserTest extends EmptyEnvironmentTestCase {
  @Test
  public void simpleStringQuote() {
    List<List<Quote>> result = QuoteParser.parse("cat");
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).size());
    Quote quote = result.get(0).get(0);
    assertEquals("cat", quote.substitute(getEmptyEnvironment()));
  }

  @Test
  public void pipeStringQuote() {
    List<List<Quote>> result = QuoteParser.parse("cat | help");
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).size());
    assertEquals(1, result.get(1).size());
  }

  @Test
  public void multipleParameters() {
    List<List<Quote>> result = QuoteParser.parse("cat ard1 ard2 | help arg | pwd");
    assertEquals(3, result.size());
    assertEquals(3, result.get(0).size());
    assertEquals(2, result.get(1).size());
    assertEquals(1, result.get(2).size());
  }

  @Test
  public void quoteParameter() {
    List<List<Quote>> result = QuoteParser.parse("cat \"arg1 arg2\"");
    assertEquals(1, result.size());
    assertEquals(2, result.get(0).size());
  }

  @Test
  public void quotedPipe() {
    List<List<Quote>> result = QuoteParser.parse("cat \"arg1 | arg2\" \'|\' ");
    assertEquals(1, result.size());
    assertEquals(3, result.get(0).size());
  }
}
