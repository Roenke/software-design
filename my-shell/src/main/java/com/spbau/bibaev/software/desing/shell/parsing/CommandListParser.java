package com.spbau.bibaev.software.desing.shell.parsing;

import com.spbau.bibaev.software.desing.shell.Environment;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.QuoteBase;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.StrongQuote;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.WeakQuote;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandListParser {
  private static final char NO_QUOTE = '#';
  private static final char STRONG_QUOTE = '\'';
  private static final char WEAK_QUOTE = '"';

  @NotNull
  static List<String> parse(@NotNull String input) {
    List<String> result = new ArrayList<>();
    final List<QuoteBase> quotes = findQuotes(input);
    List<String> commands = splitByPipe(input, quotes);
    Environment env = Environment.getInstance();
    for(String command : commands) {
      List<QuoteBase> commandQuotes = findQuotes(command);
      StringBuilder builder = new StringBuilder();
      int lastQuoteLastIx = 0;
      for(QuoteBase quote : commandQuotes) {
        builder.append(quote.beforeQuote(lastQuoteLastIx));
        builder.append(quote.substitute(env));
        lastQuoteLastIx = quote.endIndex();
      }

      builder.append(command.substring(lastQuoteLastIx));
      result.add(builder.toString());
    }

    return result;
  }

  private static List<QuoteBase> findQuotes(@NotNull String input) {
    char lastQuote = NO_QUOTE;
    int lastQuoteIx = -1;
    List<QuoteBase> quotes = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (lastQuote == NO_QUOTE) {
        lastQuote = isQuote(currentChar) ? currentChar : lastQuote;
      } else if (isQuote(currentChar) && currentChar == lastQuote) {
        QuoteBase quote = currentChar == STRONG_QUOTE
            ? new StrongQuote(input, lastQuoteIx, i)
            : new WeakQuote(input, lastQuoteIx, i);
        quotes.add(quote);
        lastQuote = NO_QUOTE;
        lastQuoteIx = -1;
      }
    }

    return quotes;
  }

  private static List<String> splitByPipe(@NotNull String input, @NotNull List<QuoteBase> quotes) {
    int lastPipeIx = -1;
    List<String> result = new ArrayList<>();
    for(int i = 0; i < input.length(); i++) {
      final int ix = i;
      if (input.charAt(i) == '|' && quotes.stream().allMatch(quoteBase -> !quoteBase.containsPosition(ix))) {
        result.add(input.substring(lastPipeIx + 1, i));
        lastPipeIx = i;
      }
    }
    result.add(input.substring(lastPipeIx + 1));

    return result;
  }

  private static boolean isQuote(char ch) {
    return STRONG_QUOTE == ch || WEAK_QUOTE == ch;
  }
}
