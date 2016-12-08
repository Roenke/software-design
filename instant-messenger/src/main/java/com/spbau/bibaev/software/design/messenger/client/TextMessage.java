package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class TextMessage extends AbstractMessage {
  private final String myText;

  public TextMessage(@NotNull NamedUser user, @NotNull Date date, @NotNull String text) {
    super(user, date);
    myText = text;
  }

  @NotNull
  @Override
  public byte[] getData() {
    return myText.getBytes();
  }

  @NotNull
  public String getText() {
    return myText;
  }
}
