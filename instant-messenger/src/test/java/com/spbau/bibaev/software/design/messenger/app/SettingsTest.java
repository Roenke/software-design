package com.spbau.bibaev.software.design.messenger.app;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vitaliy.Bibaev
 */
public class SettingsTest {
  @Test
  public void modifyTest() {
    final String newName = "newName";
    Settings.getInstance().setName(newName);
    assertEquals(newName, Settings.getInstance().getName());

    final int newPort = 10000;
    Settings.getInstance().setPort(newPort);
    assertEquals(newPort, Settings.getInstance().getPort());
  }

  @Test
  public void notifyPortChangedTest() {
    AtomicBoolean passed = new AtomicBoolean(false);
    Settings.getInstance().addListener((settings, propertyName) -> {
      if ("port".equals(propertyName)) {
        passed.set(true);
      }
    });

    Settings.getInstance().setPort(20);
    Settings.getInstance().setPort(10);

    assertTrue(passed.get());
  }

  @Test
  public void notifyNameChangedTest() {
    AtomicBoolean passed = new AtomicBoolean(false);
    Settings.getInstance().addListener((settings, propertyName) -> {
      if ("name".equals(propertyName)) {
        passed.set(true);
      }
    });

    Settings.getInstance().setName("1");
    Settings.getInstance().setName("2");

    assertTrue(passed.get());
  }
}