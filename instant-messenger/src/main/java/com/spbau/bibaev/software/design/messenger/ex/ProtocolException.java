package com.spbau.bibaev.software.design.messenger.ex;

import java.io.IOException;

/**
 * @author Vitaliy.Bibaev
 */
public class ProtocolException extends IOException {
  public ProtocolException(int errorCode) {
    super("Wrong message format. Error code = " + errorCode);
  }
}
