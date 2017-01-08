package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Vitaliy.Bibaev
 */
public class TextMessageTest {
  @Test
  public void simpleTest() throws UnknownHostException {
    final Date now = new Date();
    final TextMessage message = new TextMessage(new UserImpl("User1",
        InetAddress.getLocalHost(), 1000), now, "hello");
    assertEquals(message.getDate(), now);
    assertEquals("hello", new String(message.getData()));
    assertEquals("hello", message.getText());
    assertEquals("User1", message.getUser().getName());
    assertEquals(1000, message.getUser().getPort());
  }
}