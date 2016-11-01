package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TextMessage implements Message {
  private final String myText;
  private final User myUser;

  public TextMessage(@NotNull User user, @NotNull String text) {
    myUser = user;
    myText = text;
  }

  @Override
  public @NotNull User getReceiver() {
    return myUser;
  }

  @Override
  public @NotNull MessageType getType() {
    return MessageType.TEXT;
  }

  @Override
  public @NotNull InputStream getData() {
    return new ByteArrayInputStream(myText.getBytes());
  }
}
