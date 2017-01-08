package com.spbau.bibaev.software.design.messenger.app;

import com.spbau.bibaev.software.design.messenger.EntryPoint;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents global configuration of the application
 *
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

  /**
   * Returns a name of the current user
   *
   * @return A name of the current user
   */
  @NotNull
  public String getName() {
    return myName;
  }

  /**
   * Returns a port which listened for new messages
   *
   * @return A 16-bit port number
   */
  public int getPort() {
    return myPort;
  }

  /**
   * Rename current username
   *
   * @param name A new name for current user
   */
  public void setName(@NotNull String name) {
    if (!myName.equals(name)) {
      myName = name;
      fireStateChanged("name");
    }
  }

  /**
   * Specify a new port for listening new connection
   *
   * @param port A 16-bit port number
   */
  public void setPort(int port) {
    if (myPort != port) {
      myPort = port;
      fireStateChanged("port");
    }
  }

  /**
   * Register a new listener for modification events
   *
   * @param listener A listener object
   */
  public void addListener(@NotNull SettingsStateListener listener) {
    myListeners.add(listener);
  }

  /**
   * Returns instance of the {@link Settings}
   *
   * @return An instance
   */
  public static Settings getInstance() {
    return Settings.Holder.INSTANCE;
  }

  private void fireStateChanged(@NotNull String propertyName) {
    myListeners.forEach(listener -> listener.changed(this, propertyName));
  }
}
