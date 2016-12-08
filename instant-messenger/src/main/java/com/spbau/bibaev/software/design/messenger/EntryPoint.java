package com.spbau.bibaev.software.design.messenger;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import com.spbau.bibaev.software.design.messenger.ui.MainWindow;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.IOException;

public class EntryPoint {
  public static final String DEFAULT_USER_NAME = "Bob";
  public static final int DEFAULT_PORT = 48982;

  private static final int SEND_THREAD_COUNT = 10;
  private static final int RECEIVE_THREAD_COUNT = 10;

  public static void main(String[] args) {
    ArgumentParser parser = createParser();
    try {
      Namespace parseResult = parser.parseArgs(args);
      final String username = parseResult.get("username");
      final int port = parseResult.get("port");

      final Settings settings = Settings.getInstance();
      settings.setPort(port);
      settings.setName(username);

      Application.getInstance().registerService(MessageSendingService.class,
          new MessageSendingService(SEND_THREAD_COUNT));

      final MessageReceiverService receiverService = new MessageReceiverService(RECEIVE_THREAD_COUNT);
      Application.getInstance().registerService(MessageReceiverService.class,
          receiverService);

      settings.addListener((setts, propertyName) -> {
        if ("port".equals(propertyName)) {
          try {
            receiverService.detachAll();
            receiverService.attach(setts.getPort());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });

      MainWindow mainWindow = new MainWindow();
      mainWindow.setVisible(true);

    } catch (ArgumentParserException e) {
      parser.handleError(e);
    }
  }


  private static ArgumentParser createParser() {
    ArgumentParser parser = ArgumentParsers.newArgumentParser("client")
        .description("Yet another p2p chat")
        .defaultHelp(true);

    parser.addArgument("-p", "--port")
        .type(Integer.class)
        .choices(Arguments.range(0, (1 << 16) - 1))
        .setDefault(DEFAULT_PORT)
        .help("port to receive new connections");

    parser.addArgument("-u", "--username")
        .type(String.class)
        .setDefault(DEFAULT_USER_NAME)
        .help("user name");

    return parser;
  }
}
