package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Vitaliy.Bibaev
 */
public abstract class AbstractMessage implements Message {
  private final NamedUser myUser;
  private final Date myDate;

  AbstractMessage(@NotNull NamedUser user, @NotNull Date date) {
    myUser = user;
    myDate = date;
  }

  @Override
  public NamedUser getUser() {
    return myUser;
  }

  @Override
  public Date getDate() {
    return myDate;
  }
}
