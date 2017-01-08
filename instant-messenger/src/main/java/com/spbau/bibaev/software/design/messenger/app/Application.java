package com.spbau.bibaev.software.design.messenger.app;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the application, provide ability to register or receive application services
 */
public class Application {
  private final Map<Class<? extends Service>, Service> myServices = new HashMap<>();

  private static class Holder {
    static final Application INSTANCE = new Application();
  }

  private Application() {
  }

  /**
   * Returns instanse of {@link Application}
   *
   * @return The {@code Application} instance
   */
  public static Application getInstance() {
    return Holder.INSTANCE;
  }

  /**
   * Register some application service
   *
   * @param klass   Class of registered service
   * @param service Instance of Service implementation
   * @param <T>     Type of the service
   */
  public <T extends Service> void registerService(@NotNull Class<T> klass, @NotNull T service) {
    myServices.put(klass, service);
  }

  /**
   * Provides the service by class
   *
   * @param klass The class of a services
   * @param <T>   Type of the service
   * @return A service
   */
  @NotNull
  public <T extends Service> T getService(Class<T> klass) {
    return klass.cast(myServices.get(klass));
  }
}
