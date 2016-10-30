package com.spbau.bibaev.software.design.messenger.util;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Disposer {
  private static Map<Disposable, CopyOnWriteArrayList<Disposable>> myParentDisposable2Disposables =
      new ConcurrentHashMap<>();

  public void register(@NotNull Disposable parent, @NotNull Disposable child) {
    if (parent == child) {
      throw new IllegalArgumentException("cannot register same object as parent and child");
    }

    if (myParentDisposable2Disposables.containsKey(parent)) {
      myParentDisposable2Disposables.put(parent, new CopyOnWriteArrayList<>());
    }

    myParentDisposable2Disposables.get(parent).add(child);
  }

  public void dispose(@NotNull Disposable disposable) {
    // TODO
  }
}
