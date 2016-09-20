package com.spbau.bibaev.software.desing.shell.command;

import com.spbau.bibaev.software.desing.shell.Environment;
import com.spbau.bibaev.software.desing.shell.parsing.quoting.Quote;
import org.jetbrains.annotations.NotNull;

public class CommandArg {
  private final Quote myQuote;

  public CommandArg(@NotNull Quote quote) {
    myQuote = quote;
  }

  @NotNull
  public String getValue() {
    return myQuote.substitute(Environment.getInstance());
  }
}
