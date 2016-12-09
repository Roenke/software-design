package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.client.Message;
import org.jetbrains.annotations.NotNull;

/**
 * An interface for all listeners of {@link MessageReceiverService}
 *
 * @author Vitaliy.Bibaev
 */
public interface ReceiverListener {
  /**
   * Will called when new {@code message} received
   *
   * @param message A new message
   */
  default void messageReceived(@NotNull Message message) {
  }
}
