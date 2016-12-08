package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
  private final JLabel myInformationLabel = new JLabel();

  public MainWindow() {
    super("Instant messenger");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setLocationRelativeTo(null);

    final Settings settings = Settings.getInstance();
    final MessageReceiverService receiverService = Application.getInstance().getService(MessageReceiverService.class);

    JPanel informationPanel = new JPanel();
    settings.addListener((setts, propertyName) -> updateInformation(setts));
    updateInformation(settings);
    informationPanel.add(myInformationLabel);

    JPanel buttonsPane = new JPanel(new GridLayout(2, 1));
    final JButton sendMessageButton = new JButton("Send message");
    final JButton settingsButton = new JButton("Settings");

    sendMessageButton.addActionListener(e -> {
      final ConnectionDialog dialog = new ConnectionDialog();
      dialog.setVisible(true);
    });

    settingsButton.addActionListener(e -> {
      final SettingsWindow settingsWindow = new SettingsWindow();
      settingsWindow.setVisible(true);
    });

    buttonsPane.add(sendMessageButton);
    buttonsPane.add(settingsButton);

    final JPanel pane = new JPanel(new BorderLayout());
    pane.add(informationPanel, BorderLayout.NORTH);
    pane.add(buttonsPane, BorderLayout.CENTER);

    getContentPane().add(pane);

    pack();
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
}
