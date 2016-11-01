package com.spbau.bibaev.software.design.messenger.client;

import org.jetbrains.annotations.NotNull;

public interface MessageSendingCallback {
  void onSuccess();
  void onFail(@NotNull Message message, @NotNull Throwable e);
}
