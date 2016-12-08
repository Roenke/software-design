package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.client.Message;
import org.jetbrains.annotations.NotNull;

/**
 * @author Vitaliy.Bibaev
 */
public interface ReceiverListener {
  default void messageReceived(@NotNull Message message) {
  }
}
