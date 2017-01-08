package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * A basic interface representing a message
 */
public interface Message {
  /**
   * @return An associated user with this message
   */
  @NotNull
  NamedUser getUser();

  /**
   * @return A date when the message was created
   */
  @NotNull
  Date getDate();

  /**
   * @return A byte array with message binary content
   */
  @NotNull
  byte[] getData();
}
