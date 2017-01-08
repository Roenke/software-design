package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Represents a text message
 */
public class TextMessage extends AbstractMessage {
  private final String myText;

  /**
   * Constructs a new instance of the {@link TextMessage}
   *
   * @param user An user associated with this message
   * @param date A date when this message was created
   * @param text A content of this message
   */
  public TextMessage(@NotNull NamedUser user, @NotNull Date date, @NotNull String text) {
    super(user, date);
    myText = text;
  }

  /**
   * @return A binary data of this message
   */
  @NotNull
  @Override
  public byte[] getData() {
    return myText.getBytes();
  }

  /**
   * @return A text of this {@link TextMessage}
   */
  @NotNull
  public String getText() {
    return myText;
  }
}
