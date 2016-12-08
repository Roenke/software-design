package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface Message {
  @NotNull
  User getUser();

  Date getDate();

  @NotNull
  MessageType getType();

  @NotNull
  byte[] getData();
}
