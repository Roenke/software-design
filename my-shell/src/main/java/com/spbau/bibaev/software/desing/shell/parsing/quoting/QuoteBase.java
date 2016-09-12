package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

public abstract class QuoteBase {
  private final String myString;
  private final int myBegin;
  private final int myEnd;
  private String myBody = null;
  QuoteBase(@NotNull String string, int begin, int end) {
    myString = string;
    myBegin = begin;
    myEnd = end;
  }

  @NotNull
  public String substitute(@NotNull Environment environment) {
    return substitute(getBody(), environment);
  }

  public int endIndex() {
    return myEnd;
  }

  @NotNull
  public String beforeQuote(int fromIndex) {
    return myString.substring(fromIndex, myBegin);
  }

  public boolean containsPosition(int ix) {
    return ix > myBegin && ix < myEnd;
  }

  @NotNull
  protected abstract String substitute(@NotNull String body, @NotNull Environment environment);

  @NotNull
  private String getBody() {
    if(myBody == null) {
      myBody = myString.substring(myBegin + 1, myEnd);
    }

    return myBody;
  }
}
