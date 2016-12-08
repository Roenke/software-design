package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface Message {
  @NotNull
  NamedUser getUser();

  @NotNull
  Date getDate();

  @NotNull
  byte[] getData();
}
