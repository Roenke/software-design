package com.spbau.bibaev.software.design.messenger;

import com.spbau.bibaev.software.design.messenger.ui.MainWindow;

public class EntryPoint {
  private int DEFAULT_PORT = 48982;
  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
    mainWindow.setVisible(true);
  }
}
