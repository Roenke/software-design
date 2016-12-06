package com.spbau.bibaev.software.design.messenger;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import com.spbau.bibaev.software.design.messenger.ui.MainWindow;

public class EntryPoint {
  private static final int SEND_THREAD_COUNT = 1;
  private static final int DEFAULT_PORT = 48982;

  public static void main(String[] args) {
    Application.getInstance().registerService(MessageSendingService.class,
        new MessageSendingService(SEND_THREAD_COUNT));
    Application.getInstance().registerService(MessageReceiverService.class, new MessageReceiverService(2));
    MainWindow mainWindow = new MainWindow();
    mainWindow.setVisible(true);
  }
}
