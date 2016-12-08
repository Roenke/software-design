package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

/**
 * @author Vitaliy.Bibaev
 */
public interface SettingsStateListener {
  void changed(@NotNull Settings settings, @NotNull String propertyName);
}
