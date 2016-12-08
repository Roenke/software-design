package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class TextMessage extends AbstractMessage {
  private final String myText;

  public TextMessage(@NotNull User user, @NotNull Date date, @NotNull String text) {
    super(user, date, MessageType.TEXT);
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
