package com.spbau.bibaev.software.design.messenger.client;

import org.jetbrains.annotations.NotNull;

public interface MessageSendingCallback {
  void onSuccess(@NotNull Message message);

  void onFail(@NotNull Message message, @NotNull Throwable e);
}
