package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Data class-implementation of {@link Message}
 *
 * @author Vitaliy.Bibaev
 */
public abstract class AbstractMessage implements Message {
  private final NamedUser myUser;
  private final Date myDate;

  /**
   * Constructs a new instance of {@link Message}
   *
   * @param user An user which associated with current dialog
   * @param date A date of message creating
   */
  AbstractMessage(@NotNull NamedUser user, @NotNull Date date) {
    myUser = user;
    myDate = date;
  }

  /**
   * @return An user with name
   */
  @NotNull
  @Override
  public NamedUser getUser() {
    return myUser;
  }

  /**
   * @return A Date when the message was created
   */
  @NotNull
  @Override
  public Date getDate() {
    return myDate;
  }
}
