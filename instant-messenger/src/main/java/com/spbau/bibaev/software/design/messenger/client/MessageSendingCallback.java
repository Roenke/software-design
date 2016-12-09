package com.spbau.bibaev.software.design.messenger.client;

import org.jetbrains.annotations.NotNull;

/**
 * A callback for {@link MessageSendingService}
 */
public interface MessageSendingCallback {
  /**
   * Will be called when {@link Message} successfully delivered
   *
   * @param message An associated message
   */
  void onSuccess(@NotNull Message message);

  /**
   * Will be called when {@link Message} could not be delivered
   *
   * @param message An associated message
   * @param e       A cause of the failure
   */
  void onFail(@NotNull Message message, @NotNull Throwable e);
}
