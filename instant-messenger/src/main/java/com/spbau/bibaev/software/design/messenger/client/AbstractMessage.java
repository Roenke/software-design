package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.User;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Vitaliy.Bibaev
 */
public abstract class AbstractMessage implements Message {
  private final User myUser;
  private final Date myDate;
  private final MessageType myType;

  protected AbstractMessage(@NotNull User user, @NotNull Date date, @NotNull MessageType type) {
    myUser = user;
    myType = type;
    myDate = date;
  }

  @Override
  public @NotNull User getUser() {
    return myUser;
  }

  @Override
  public Date getDate() {
    return myDate;
  }

  @Override
  public @NotNull MessageType getType() {
    return myType;
  }
}
