package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.app.User;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import com.spbau.bibaev.software.design.messenger.server.ReceiverListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MainWindow extends JFrame {
  private final JLabel myInformationLabel = new JLabel();
  private Map<MyConnection, ConversationWindow> myOpenDialogs = new ConcurrentHashMap<>();

  public MainWindow() {
    super("Instant messenger");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setLocationRelativeTo(null);

    final Settings settings = Settings.getInstance();
    final MessageReceiverService receiverService = Application.getInstance().getService(MessageReceiverService.class);
    receiverService.attach(Settings.getInstance().getPort());
    receiverService.addListener(new ReceiverListener() {
      @Override
      public void messageReceived(@NotNull Message message) {
        final ConversationWindow dialog = getDialogWindow(message.getUser());
        dialog.setVisible(true);
        dialog.pushMessage(message);
      }
    });

    JPanel informationPanel = new JPanel();
    settings.addListener((setts, propertyName) ->

        updateInformation(setts));

    updateInformation(settings);
    informationPanel.add(myInformationLabel);

    JPanel buttonsPane = new JPanel(new GridLayout(2, 1));
    final JButton sendMessageButton = new JButton("Send message");
    final JButton settingsButton = new JButton("Settings");

    sendMessageButton.addActionListener(e ->

    {
      final ConnectionDialog dialog = new ConnectionDialog(MainWindow.this);
      final User user = dialog.showDialog();
      if (user != null) {
        getDialogWindow(user).setVisible(true);
      }
    });

    settingsButton.addActionListener(e -> {
      final SettingsDialog settingsDialog = new SettingsDialog(MainWindow.this);
      final String newName = settingsDialog.showDialog();
      if (newName != null && !newName.trim().isEmpty()) {
        Settings.getInstance().setName(newName);
      }
    });

    buttonsPane.add(sendMessageButton);
    buttonsPane.add(settingsButton);

    final JPanel pane = new JPanel(new BorderLayout());
    pane.add(informationPanel, BorderLayout.NORTH);
    pane.add(buttonsPane, BorderLayout.CENTER);

    getContentPane().add(pane);

    pack();
  }

  @NotNull
  private ConversationWindow getDialogWindow(@NotNull User user) {
    final MyConnection connection = new MyConnection(user);

    return myOpenDialogs.computeIfAbsent(connection, x -> {
      final ConversationWindow conversationWindow = new ConversationWindow(x.myAddress, x.myPort);
      conversationWindow.setLocationRelativeTo(this);
      conversationWindow.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          myOpenDialogs.remove(connection);
        }
      });
      return conversationWindow;
    });
  }

  private void updateInformation(@NotNull Settings settings) {
    if (SwingUtilities.isEventDispatchThread()) {
      final String info = String.format("Current settings: name = %s; port = %d",
          settings.getName(), settings.getPort());
      myInformationLabel.setText(info);
    } else {
      SwingUtilities.invokeLater(() -> updateInformation(settings));
    }
  }

  private static class MyConnection {
    private final InetAddress myAddress;
    private final int myPort;

    MyConnection(@NotNull User user) {
      myAddress = user.getAddress();
      myPort = user.getPort();
    }

    @Override
    public int hashCode() {
      return Objects.hash(myAddress, myPort);
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof MyConnection &&
          myAddress.equals(((MyConnection) obj).myAddress) && myPort == ((MyConnection) obj).myPort;
    }
  }
}
