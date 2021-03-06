package com.spbau.bibaev.software.design.shell.parsing;

import com.spbau.bibaev.software.design.shell.EmptyEnvironmentTestCase;
import com.spbau.bibaev.software.design.shell.parsing.quoting.Quote;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuoteParserTest implements EmptyEnvironmentTestCase {
  private final QuoteParser myParser = new QuoteParser();

  @Test
  public void simpleStringQuote() {
    List<List<Quote>> result = myParser.parse("cat");
    assertEquals(1, result.size());
    assertEquals(1, result.get(0).size());
    Quote quote = result.get(0).get(0);
    assertEquals("cat", quote.substitute(getEmptyEnvironment()));
  }

  @Test
  public void pipeStringQuote() {
    List<List<Quote>> result = myParser.parse("cat | help");
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).size());
    assertEquals(1, result.get(1).size());
  }

  @Test
  public void multipleParameters() {
    List<List<Quote>> result = myParser.parse("cat ard1 ard2 | help arg | pwd");
    assertEquals(3, result.size());
    assertEquals(3, result.get(0).size());
    assertEquals(2, result.get(1).size());
    assertEquals(1, result.get(2).size());
  }

  @Test
  public void quoteParameter() {
    List<List<Quote>> result = myParser.parse("cat \"arg1 arg2\"");
    assertEquals(1, result.size());
    assertEquals(2, result.get(0).size());
  }

  @Test
  public void quotedPipe() {
    List<List<Quote>> result = myParser.parse("cat \"arg1 | arg2\" \'|\' ");
    assertEquals(1, result.size());
    assertEquals(3, result.get(0).size());
  }
}
