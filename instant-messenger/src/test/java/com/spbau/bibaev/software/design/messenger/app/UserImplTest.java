package com.spbau.bibaev.software.design.messenger.app;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * @author Vitaliy.Bibaev
 */
public class UserImplTest {
  private final UserImpl user = new UserImpl("user1", InetAddress.getLocalHost(), 100);
  private final UserImpl user1Copy = new UserImpl("user1", InetAddress.getLocalHost(), 100);

  private final UserImpl userDifferentPort = new UserImpl("user1", InetAddress.getLocalHost(), 101);
  private final UserImpl userDifferentName = new UserImpl("user2", InetAddress.getLocalHost(), 100);
  private final UserImpl userDifferentAddress = new UserImpl("user1",
      InetAddress.getByAddress(new byte[]{1, 1, 1, 1}), 100);

  public UserImplTest() throws UnknownHostException {
  }

  @Test
  public void hashCodeTest() throws UnknownHostException {
    assertEquals(user.hashCode(), user1Copy.hashCode());
    assertEquals(user.hashCode(), userDifferentName.hashCode());

    assertNotEquals(user.hashCode(), userDifferentAddress.hashCode());
    assertNotEquals(user.hashCode(), userDifferentPort.hashCode());
  }

  @Test
  public void equalsTest() throws UnknownHostException {
    assertEquals(user, user1Copy);
    assertEquals(user, userDifferentName);

    assertNotEquals(user, userDifferentAddress);
    assertNotEquals(user, userDifferentPort);
  }
}