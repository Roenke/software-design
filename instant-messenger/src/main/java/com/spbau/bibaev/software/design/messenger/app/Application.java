package com.spbau.bibaev.software.design.messenger.app;

import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Application {
  private Map<Class<? extends Service>, Service> myServices= new HashMap<Class<? extends Service>, Service>();

  private static class Holder {
    static final Application INSTANCE = new Application();
  }

  private Application() {
  }

  public static Application getInstance() {
    return Holder.INSTANCE;
  }

  public <T extends Service> void registerService(@NotNull Class<T> klass, @NotNull T service) {
    myServices.put(klass, service);
  }

  @NotNull
  public <T extends Service> T getService(Class<T> klass) {
    return klass.cast(myServices.get(klass));
  }
}
