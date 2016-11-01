package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public interface Message {
  @NotNull
  User getReceiver();

  @NotNull
  MessageType getType();

  @NotNull
  InputStream getData();
}
