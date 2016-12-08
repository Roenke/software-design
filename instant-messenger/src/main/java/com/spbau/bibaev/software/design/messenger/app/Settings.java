package com.spbau.bibaev.software.design.messenger.app;

import com.spbau.bibaev.software.design.messenger.EntryPoint;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Vitaliy.Bibaev
 */
public class Settings {
  private volatile String myName = EntryPoint.DEFAULT_USER_NAME;
  private volatile int myPort = EntryPoint.DEFAULT_PORT;

  private final List<SettingsStateListener> myListeners = new CopyOnWriteArrayList<>();

  private static class Holder {
    static final Settings INSTANCE = new Settings();
  }

  private Settings() {
  }

  @NotNull
  public String getName() {
    return myName;
  }

  public int getPort() {
    return myPort;
  }

  public void setName(@NotNull String name) {
    if (!myName.equals(name)) {
      myName = name;
      fireStateChanged("name");
    }
  }

  public void setPort(int port) {
    if (myPort != port) {
      myPort = port;
      fireStateChanged("port");
    }
  }

  public void addListener(@NotNull SettingsStateListener listener) {
    myListeners.add(listener);
  }

  public static Settings getInstance() {
    return Settings.Holder.INSTANCE;
  }

  private void fireStateChanged(@NotNull String propertyName) {
    myListeners.forEach(listener -> listener.changed(this, propertyName));
  }
}
