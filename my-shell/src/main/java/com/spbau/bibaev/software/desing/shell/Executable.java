package com.spbau.bibaev.software.desing.shell;


import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface Executable {
  void perform(@NotNull BufferedReader reader, @NotNull BufferedWriter writer) throws IOException;
}
