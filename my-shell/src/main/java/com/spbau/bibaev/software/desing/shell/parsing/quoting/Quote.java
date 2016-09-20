package com.spbau.bibaev.software.desing.shell.parsing.quoting;

import com.spbau.bibaev.software.desing.shell.Environment;
import org.jetbrains.annotations.NotNull;

public interface Quote {
  @NotNull
  String substitute(@NotNull Environment environment);
}
