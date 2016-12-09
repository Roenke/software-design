package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

/**
 * Listener interface for {@link Settings} object modification
 *
 * @author Vitaliy.Bibaev
 */
public interface SettingsStateListener {
  /**
   * Fires if some property of the {@link Settings} object was changed
   *
   * @param settings     An instance of {@link Settings}
   * @param propertyName A name of the property which changed
   */
  void changed(@NotNull Settings settings, @NotNull String propertyName);
}
